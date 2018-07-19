package com.aiwac.constant;

public class Constant {
	public static final int IDENTIFYINGCODERANGE = 9000;
	public static final int IDENTIFYINGCODEBASENUM = 1000;
	public static final long CRYING_INTERVAL_TIME = 5 * 60;
	
	public static final String OPT = "opt";
	public static final String USER = "user";
	public static final String RESULT = "result";
	public static final String CODE = "code";
	
	public static final String ISPHONENUMBEREXIST = "isPhoneNumberExist";
	public static final String REGISTERERROR = "registerError";
	public static final String ERROR = "error";
	public static final String ERRORCODE = "errorCode";
	public static final String PARAERROR = "paraError";
	public static final String OPTERROR = "optError";
	public static final String PARAGETIDENTIFYCODEERROR ="not_have_getIdentifyCode";
	public static final String GETINDENTIFYINGCODEOP = "getIdentifyCode";
	public static final String IDENTIFYINGCODEVERIFIED = "identifyingCodeVerified";
	public static final String LOGINWITHIDENTIFYINGCODE = "loginWithIdentifyingCode";
	public static final String LOGINWITHPASSWD = "loginWithPasswd";
	public static final String LOGINRESULT = "loginResult";
	public static final String MODIFYPASSWDRESULT = "modifyPasswdResult";
	public static final String REQUEST = "REQUEST";
	
	public static final int PHONENUMBEREXIST = 1;
	public static final int PHONENUMBERNOTEXIST = 0;
	public static final int REGISTERSUCCESS = 1;
	public static final int REGISTERFAIL = 0;
	public static final int INPUTINFOERROR = 1;
	public static final int PARAEMPTYERROR = 2;
	
	public static final String EMPTYSTRING = "";
	public static final String LOGINWITHMSG = "loginWithMsg";
	public static final String TRYREGISTER = "tryRegister";
	public static final String MODIFYPASSWD = "modifyPasswd";
	public static final String PHONENUMBER = "phonenumber";
	public static final String PASSWD = "passwd";
	
	//file service
	public static final String PREFIX = "/DataWarehouse";
	public static final String PHOTOPOSTFIX = "Photo";
	public static final String QUESTIONFILENAME = "Quesion";
	public static final String ANSWERFILENAME = "Answer";
	public static final String RETRANSMISSIONPREFIX = "/retransmission";
	public static final String RETRANSMISSIONPOSTFIX = ".txt";
	
	public static final int FILETYPEWAV = 1;
	public static final int FILETYPEJPG = 2;
	public static final int FILETYPEMP3 = 3;
	
	public static final String CONTENTPREFIX = "/VoiceData";
	public static final String GAME = "/Game";
	public static final String STORY = "/Story";
	public static final String SONG = "/Song";
	
	public static final String FACE = "face";
	public static final String VOICE = "voice";
	public static final String CRYPHOTOPREFIX = "/CryPhoto";
	public static final String CRYVOICEPREFIX = "/CryVoice";
	public static final String JPG = ".jpg";
	public static final String WAV = ".wav";
	
	//public static final String MODEL_BASE_DIR = "C:\\models";
	public static final String MODEL_BASE_DIR = "/root/models";
	
	public static final int EMOTIONTYPES = 7;
	
	//isActiveConversationEmotion
	public static final int EMOTION_CONTINUE_TIMES = 2;
	public static final int EMOTION_INTERVAL_TIME = 15000;
}
