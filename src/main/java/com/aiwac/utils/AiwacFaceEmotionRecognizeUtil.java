package com.aiwac.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.types.UInt8;

import com.aiwac.constant.Constant;

/**
*
* @author HC
* @date 2017年12月6日
*
*/

public class AiwacFaceEmotionRecognizeUtil {
	private static final Logger logger = LogManager.getLogger(AiwacFaceEmotionRecognizeUtil.class);
	//private static final String MODEL_DIR = Constant.MODEL_BASE_DIR + "\\FaceEmotionModel\\face_graph.pb";
	//private static final String TMP_PATH = Constant.MODEL_BASE_DIR + "\\tmp\\";
	private static final String MODEL_DIR = Constant.MODEL_BASE_DIR + "/FaceEmotionModel/face_graph.pb";
	private static final String TMP_PATH = Constant.MODEL_BASE_DIR + "/tmp/"; 
	private static final int IMAGE_SIZE = 56;
	private static final int CHANNEL = 1;
	private static final byte[] GRAPH_DEF;

	static {
		logger.info("load face emotion recognize model");
		GRAPH_DEF = readAllBytesOrExit(Paths.get(MODEL_DIR));
	}
	
	public static void loadGraph() {}
	
	public static float[] labelImage(String username, byte[] imageBytes) {
		float[] labelProbabilities = new float[] {0f, 0f, 0f, 0f, 0f, 0f, 0f};
		
		if (imageBytes != null) {
			logger.info("detect face emotion from user {}", username);
			//if not detected, use original picture
			Tensor<Float> img_tensor_byte = constructAndExecuteGraphToNormalizeImage(imageBytes, IMAGE_SIZE, CHANNEL);
			labelProbabilities = executeInceptionGraph(GRAPH_DEF, img_tensor_byte);
		}
		
		return labelProbabilities;
	}

	private static Tensor<Float> constructAndExecuteGraphToNormalizeImage(byte[] imageBytes, int image_size, int channel) {
		try (Graph g = new Graph()) {
			GraphBuilder b = new GraphBuilder(g);
			// Some constants specific to the pre-trained model at:
			// https://storage.googleapis.com/download.tensorflow.org/models/inception5h.zip
			//
			// - The model was trained with images scaled to 224x224 pixels.
			// - The colors, represented as R, G, B in 1-byte each were converted to
			//   float using (value - Mean)/Scale.
			final int H = image_size;
			final int W = image_size;

			// Since the graph is being constructed once per execution here, we can use a constant for the
			// input image. If the graph were to be re-used for multiple input images, a placeholder would
			// have been more appropriate.
			final Output<String> input = b.constant("input", imageBytes);
			final Output<Float> output =
					b.resizeBilinear(
				              b.expandDims(
				                  b.cast(b.decodeJpeg(input, channel), Float.class), 
				                  b.constant("make_batch", 0)), 
				              b.constant("size", new int[] {H, W}));
			try (Session s = new Session(g)) {
				return s.runner().fetch(output.op().name()).run().get(0).expect(Float.class);
			}
		}
	}

	private static float[] executeInceptionGraph(byte[] graphDef, Tensor<Float> image) {
		float kpf = (float) 1.0;
		Tensor<Float> kp = Tensor.create(kpf, Float.class);
		
		try (Graph g = new Graph()) {
			g.importGraphDef(graphDef);
			try (Session s = new Session(g);
					Tensor<Float> result =
							s.runner().feed("input", image).feed("keep_prob", kp).fetch("output").
							run().get(0).expect(Float.class)) {
				final long[] rshape = result.shape();
				if (result.numDimensions() != 2 || rshape[0] != 1) {
					throw new RuntimeException(
							String.format(
									"Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
									Arrays.toString(rshape)));
				}
				int nlabels = (int) rshape[1];
				return result.copyTo(new float[1][nlabels])[0];
			}catch(Exception e) {
				LoggerUtil.LogException(logger, e);
			}
		}
		return null;
	}

	private static byte[] readAllBytesOrExit(Path path) {
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			logger.error("Failed to read file {} ", path);
			LoggerUtil.LogException(logger, e);
		}
		return null;
	}

	// In the fullness of time, equivalents of the methods of this class should be auto-generated from
	// the OpDefs linked into libtensorflow_jni.so. That would match what is done in other languages
	// like Python, C++ and Go.
	static class GraphBuilder {
		GraphBuilder(Graph g) {
			this.g = g;
		}

		Output<Float> div(Output<Float> x, Output<Float> y) {
			return binaryOp("Div", x, y);
		}

		<T> Output<T> sub(Output<T> x, Output<T> y) {
			return binaryOp("Sub", x, y);
		}

		<T> Output<Float> resizeBilinear(Output<T> images, Output<Integer> size) {
			return binaryOp3("ResizeBilinear", images, size);
		}

		<T> Output<T> expandDims(Output<T> input, Output<Integer> dim) {
			return binaryOp3("ExpandDims", input, dim);
		}

		<T, U> Output<U> cast(Output<T> value, Class<U> type) {
			DataType dtype = DataType.fromClass(type);
			return g.opBuilder("Cast", "Cast")
					.addInput(value)
					.setAttr("DstT", dtype)
					.build()
					.<U>output(0);
		}

		Output<UInt8> decodeJpeg(Output<String> contents, long channels) {
			return g.opBuilder("DecodeJpeg", "DecodeJpeg")
					.addInput(contents)
					.setAttr("channels", channels)
					.build()
					.<UInt8>output(0);
		}

		<T> Output<T> constant(String name, Object value, Class<T> type) {
			try (Tensor<T> t = Tensor.<T>create(value, type)) {
				return g.opBuilder("Const", name)
						.setAttr("dtype", DataType.fromClass(type))
						.setAttr("value", t)
						.build()
						.<T>output(0);
			}
		}
		Output<String> constant(String name, byte[] value) {
			return this.constant(name, value, String.class);
		}
	
		Output<Integer> constant(String name, int value) {
			return this.constant(name, value, Integer.class);
		}
	
		Output<Integer> constant(String name, int[] value) {
			return this.constant(name, value, Integer.class);
		}
	
		Output<Float> constant(String name, float value) {
			return this.constant(name, value, Float.class);
		}
	
		private <T> Output<T> binaryOp(String type, Output<T> in1, Output<T> in2) {
			return g.opBuilder(type, type).addInput(in1).addInput(in2).build().<T>output(0);
		}
	
		private <T, U, V> Output<T> binaryOp3(String type, Output<U> in1, Output<V> in2) {
			return g.opBuilder(type, type).addInput(in1).addInput(in2).build().<T>output(0);
		}
		
		private Graph g;
	}
	
}
