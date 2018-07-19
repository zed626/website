package com.aiwac.bean.response;

public class WeChatSignature {
	private String noncestr;
    private String timestamp;
    private String signature;
    public void setNoncestr(String noncestr) {
         this.noncestr = noncestr;
     }
     public String getNoncestr() {
         return noncestr;
     }

    public void setTimestamp(String timestamp) {
         this.timestamp = timestamp;
     }
     public String getTimestamp() {
         return timestamp;
     }

    public void setSignature(String signature) {
         this.signature = signature;
     }
     public String getSignature() {
         return signature;
     }
}
