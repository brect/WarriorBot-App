package br.com.padawan.botjs.vo;

public class UsuarioVO {

    private String sessionId;

    public UsuarioVO() {
    }

    public UsuarioVO(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
