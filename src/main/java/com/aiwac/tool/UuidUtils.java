package com.aiwac.tool;
/**
*
* @author LMC
* @date 2017年10月31日
*
*/
import java.util.UUID;

public class UuidUtils {
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
     }
}
