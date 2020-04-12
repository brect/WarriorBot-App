package br.com.padawan.botjs.services;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONObject;

import br.com.padawan.botjs.helper.HttpRequest;
import br.com.padawan.botjs.vo.SessionVO;

public class GetSessionService extends AsyncTask<Void, String, String> {

    private Context context;

    private HttpRequest httpRequest;
    private MutableLiveData<SessionVO> sessionListMutableLiveData;

    private String result;

    public GetSessionService(HttpRequest httpRequest, MutableLiveData<SessionVO> sessionListMutableLiveData) {
        this.httpRequest = httpRequest;
        this.sessionListMutableLiveData = sessionListMutableLiveData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        String url = "https://warriorbotjs.herokuapp.com/assistant/session";

        result = httpRequest.sendPostHttpConnection(url);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject obj = new JSONObject(result);
            if(obj.get("status").equals(200)) {
                SessionVO sessionVO = new SessionVO(obj);
                sessionListMutableLiveData.postValue(sessionVO);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
