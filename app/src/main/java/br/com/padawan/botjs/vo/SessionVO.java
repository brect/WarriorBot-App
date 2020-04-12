package br.com.padawan.botjs.vo;

import org.json.JSONObject;

public class SessionVO {

    private int status;
    private String sessionId;

    public SessionVO(JSONObject obj) {
        this.setStatus(obj.optInt("status"));
        this.setSessionId(obj.optString("session_id"));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SessionVO(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
