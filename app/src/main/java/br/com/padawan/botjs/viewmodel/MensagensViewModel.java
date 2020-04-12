package br.com.padawan.botjs.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.padawan.botjs.helper.HttpRequest;
import br.com.padawan.botjs.services.SendMessageService;
import br.com.padawan.botjs.vo.MensagemVO;

public class MensagensViewModel extends AndroidViewModel {

    private MutableLiveData<MensagemVO> mensagemMutableLiveData = new MutableLiveData<>();
    private HttpRequest httpRequest;

    public MensagensViewModel(@NonNull Application application) {
        super(application);
        httpRequest = new HttpRequest(application);
    }

    public MutableLiveData<MensagemVO> getMensagensListLiveData(){
        return mensagemMutableLiveData;
    }

    public void callMessageService(MensagemVO mensagemVO){
        new SendMessageService(httpRequest, mensagemVO, mensagemMutableLiveData).execute();
    }
}
