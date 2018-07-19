<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加场景</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/scene/addScene" method="post" enctype="multipart/form-data" id="sceneForm">
		<input type=hidden name=courseGroupId value=${courseGroupId}>
		场景名称: <input type="text" name="name"><br>
		场景对应图片数目: <input type="text" name="number"><br>
		
		<br>
		情绪对应关系：0:Angry,1:Disgust,2:Fear,3:Happy,4:Sad5:Surprise,6:Neutral<br>
		<br>
		
		背景音乐<br>
		选择音乐:<input type="file" name="music">
		<br>
		
		图片1<br>
		选择图片:<input type="file" name="picture1"><br>
		对应情绪：<input type="text" name="label1" ><br>
		
		<br>
		图片2<br>
		选择图片:<input type="file" name="picture2"><br>
		对应情绪：<input type="text" name="label2" ><br>
		
		<br>
		图片3<br>
		选择图片:<input type="file" name="picture3"><br>
		对应情绪：<input type="text" name="label3" ><br>
		
		<br>
		图片4<br>
		选择图片:<input type="file" name="picture4"><br>
		对应情绪：<input type="text" name="label4" ><br>
		
		<br>
		图片5<br>
		选择图片:<input type="file" name="picture5"><br>
		对应情绪：<input type="text" name="label5" ><br>
		
		<br>
		图片6<br>
		选择图片:<input type="file" name="picture6"><br>
		对应情绪：<input type="text" name="label6" ><br>
		
		<br>
		图片7<br>
		选择图片:<input type="file" name="picture7"><br>
		对应情绪：<input type="text" name="label7" ><br>
		
		<br>
		<input id="reset" type="reset">
		<input id="submit" type="submit">
	</form>
	
	<br>
	<textarea rows="4" cols="50" name="courseDesc" form="courseForm">场景描述</textarea>
</body>
</html>