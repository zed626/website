package com.aiwac.bean.request;

/**
 * @author zed
 *
 */
public class WeChatJsTicket {
	private int errcode;
    private String errmsg;
    private String ticket;
    private int expires_in;
    public void setErrcode(int errcode) {
         this.errcode = errcode;
     }
     public int getErrcode() {
         return errcode;
     }

    public void setErrmsg(String errmsg) {
         this.errmsg = errmsg;
     }
     public String getErrmsg() {
         return errmsg;
     }

    public void setTicket(String ticket) {
         this.ticket = ticket;
     }
     public String getTicket() {
         return ticket;
     }

    public void setExpires_in(int expires_in) {
         this.expires_in = expires_in;
     }
     public int getExpires_in() {
         return expires_in;
     }
}
