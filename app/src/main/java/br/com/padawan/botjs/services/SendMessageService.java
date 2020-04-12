package br.com.padawan.botjs.services;


import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.padawan.botjs.helper.HttpRequest;
import br.com.padawan.botjs.vo.MensagemVO;

public class SendMessageService extends AsyncTask<Void, String, String> {

    private Context context;

    private HttpRequest httpRequest;
    private MensagemVO mensagemVO;
    private MutableLiveData<MensagemVO> mensagemMutableLiveData;

    private String result;

    public SendMessageService(HttpRequest httpRequest, MensagemVO mensagemVO, MutableLiveData<MensagemVO> mensagemVOMutableLiveData) {
        this.httpRequest = httpRequest;
        this.mensagemVO = mensagemVO;
        this.mensagemMutableLiveData = mensagemVOMutableLiveData;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        String url = "https://warriorbotjs.herokuapp.com/assistant/message";
        result = httpRequest.sendPostWithParamsHttpConnection(url, mensagemVO);
        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject obj = new JSONObject(result);

            if(obj.get("output") != null) {

                JSONArray resultArray = obj.optJSONArray("output");
                MensagemVO mensagemVO = new MensagemVO();

                mensagemVO.setOutput(obj.optJSONArray("output").get(0).toString());
                mensagemMutableLiveData.postValue(mensagemVO);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}