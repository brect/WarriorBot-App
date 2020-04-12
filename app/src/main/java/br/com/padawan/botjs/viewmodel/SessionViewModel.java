package br.com.padawan.botjs.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import br.com.padawan.botjs.helper.HttpRequest;
import br.com.padawan.botjs.services.GetSessionService;
import br.com.padawan.botjs.vo.SessionVO;

public class SessionViewModel  extends AndroidViewModel {

    private MutableLiveData<SessionVO> sessionListMutableLiveData = new MutableLiveData<>();
    private HttpRequest httpRequest;

    public SessionViewModel(@NonNull Application application) {
        super(application);
        httpRequest = new HttpRequest(application);
    }

    public MutableLiveData<SessionVO> getSessionListMutableLiveData(){
        return sessionListMutableLiveData;
    }

    public void callSessionService(){
        new GetSessionService(httpRequest, sessionListMutableLiveData).execute();
    }

}
