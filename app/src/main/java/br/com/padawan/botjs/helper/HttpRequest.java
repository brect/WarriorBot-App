package br.com.padawan.botjs.helper;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.padawan.botjs.vo.MensagemVO;

public class HttpRequest {

    final Context context;
    private HttpURLConnection connection;


    public HttpRequest(Context context) {
        this.context = context;
    }




    public String sendPostHttpConnection(String params){
        return sendPostSession(params);
    }

    public String sendPostWithParamsHttpConnection(String params, MensagemVO mensagemVO){
        return sendPostMessage(params, mensagemVO);
    }

    public String sendPostSession(String params)  {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            url = new URL(params);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            if(urlConnection.getResponseCode() == 200){
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
                return result;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendPostMessage(String postUrl , MensagemVO postDataParams) {
        String url= postUrl;
        String result = "";

        try {
            URL object=new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestMethod("POST");

            JSONObject cred = new JSONObject();
            cred.put("session_id",postDataParams.getSessionVO().getSessionId());
            cred.put("input",postDataParams.getInput());

            DataOutputStream localDataOutputStream = new DataOutputStream(con.getOutputStream());
            localDataOutputStream.writeBytes(cred.toString());
            localDataOutputStream.flush();
            localDataOutputStream.close();

            if(con.getResponseCode() == 200){
                InputStream in = new BufferedInputStream(con.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
                return result;
            }

        }
        catch (Exception e){
            Log.v("ErrorAPP",e.toString());
        }
        return "";
    }
}

