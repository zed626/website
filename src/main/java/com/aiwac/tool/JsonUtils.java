package com.aiwac.tool;

import java.util.HashMap;
import java.util.Map;

import com.aiwac.constant.Constant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
*
* @author lmc
* @date 2017.10.24
* encode data
*/


public class JsonUtils {
	public static String encodeStringToJson(Map<String,Object> mp) {
		if(mp != null) {
			JSONObject ob = JSON.parseObject(JSON.toJSONString(mp));
			System.out.println(ob.toString());
			return ob.toString();
		}
		return Constant.EMPTYSTRING;
	}
	
	public static String encodeStringToJsonArray(Map<String,Object> mp) {
		if(mp != null) {
			JSONArray ob = JSON.parseArray(JSON.toJSONString(mp));
			System.out.println(ob.toString());
			return ob.toString();
		}
		return Constant.EMPTYSTRING;
	}
	
	public static Map<String,Object> decodeJsonToMap(String str) {
		System.out.println("decodeJsonToMap");
		JSONObject ob = JSON.parseObject(str);
		Map<String,Object> mp = JSONObject.toJavaObject(ob, Map.class);
		return mp;
	}
	
	//json has two param. key1:value1,key2:value2
	public static String encodeResultToJson2(String fKey,Object fValue,String sKey,Object sValue) {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put(fKey, fValue);
		mp.put(sKey, sValue);
		System.out.println("encodeResultToJson2 map: "+mp);
		return encodeStringToJson(mp);
	} 
	
	public static String encodeIdentifyingCodeAndSearchResultToJson(String phoneNum,String code,boolean isExist) {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put(Constant.OPT, Constant.GETINDENTIFYINGCODEOP);
		Map<String, Object> nestMp = new HashMap<String, Object>();
		nestMp.put(Constant.PHONENUMBER, phoneNum);
		if(isExist) {
			nestMp.put(Constant.ISPHONENUMBEREXIST, Constant.PHONENUMBEREXIST);
		}
		else {
			nestMp.put(Constant.ISPHONENUMBEREXIST, Constant.PHONENUMBERNOTEXIST);
		}
		nestMp.put(Constant.CODE, code);
		mp.put(Constant.USER, nestMp);
		JSONArray jsnArr = new JSONArray();
		jsnArr.add(JSONObject.parse(JSON.toJSONString(nestMp)));
		mp.put(Constant.USER, jsnArr);
		return encodeStringToJson(mp);
	}
	
	//generate json of error info 
	public static String encodeErrorCodeToJson(String error) {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put(Constant.OPT, Constant.ERROR);
		mp.put(Constant.ERRORCODE, error);
		return encodeStringToJson(mp);
	}
	
	public static String getValueFromJsonUsingSecondKey(String str,String primaryKey,String secondKey) {
		System.out.println("getValueFromJsonUsingSecondKey str: "+str+" primaryKey: "+primaryKey+" secondKey: "+secondKey);
		Map<String,Object> mp = JsonUtils.decodeJsonToMap(str);
		JSONArray ob = JSON.parseArray(mp.get(primaryKey).toString());
		System.out.println(" JSON.parseArray(mp.get(secondKey).toString()  " + ob);
		if(ob.size()>0 && ((JSONObject)ob.get(0)).containsKey(secondKey)) {
			return getValueFromJsonUsingPrimaryKey(((JSONObject)ob.get(0)).toString(),secondKey);	
		}
		return Constant.EMPTYSTRING;
	}
	
	public static String getValueFromJsonUsingPrimaryKey(String str,String strKey) {
		System.out.println("getValueFromJsonUsingPrimaryKey key: " + strKey);
		Map<String,Object> mp = JsonUtils.decodeJsonToMap(str);
		if(mp.containsKey(strKey)) {
			return mp.get(strKey).toString();
		}
		return Constant.EMPTYSTRING;
	}
	
}
