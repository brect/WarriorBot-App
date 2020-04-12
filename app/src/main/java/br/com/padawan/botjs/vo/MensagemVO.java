package br.com.padawan.botjs.vo;

import org.json.JSONObject;

public class MensagemVO {

    private TipoUsuario tipoUsuario;
    private SessionVO sessionVO;
    private String input;
    private String output;

    public MensagemVO() {
    }

    public MensagemVO(JSONObject obj, TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.sessionVO.setSessionId(obj.optString("session_id"));
        this.input = obj.optString("input");
        this.output = obj.optString("output");
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public SessionVO getSessionVO() {
        return sessionVO;
    }

    public void setSessionVO(SessionVO sessionVO) {
        this.sessionVO = sessionVO;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
