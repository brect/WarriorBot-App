package br.com.padawan.botjs.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import br.com.padawan.botjs.R;
import br.com.padawan.botjs.adapter.MensagensAdapter;
import br.com.padawan.botjs.viewmodel.MensagensViewModel;
import br.com.padawan.botjs.viewmodel.SessionViewModel;
import br.com.padawan.botjs.vo.MensagemVO;
import br.com.padawan.botjs.vo.SessionVO;
import br.com.padawan.botjs.vo.TipoUsuario;

public class ChatActivity extends AppCompatActivity {

    private EditText boxMensagem;
    private ImageView enviarImagem;
    private FloatingActionButton fabEnviar;

    private SessionViewModel sessionViewModel;
    private MensagensViewModel mensagensViewModel;

    private List<MensagemVO> mensagemDataArrayList = new ArrayList<>();

    private MensagemVO mensagemVO = new MensagemVO();

    private EventListener addChildEventListener;

    private RecyclerView recyclerMensagens;
    private MensagensAdapter mensagensAdapter;

    private List<MensagemVO> mensagensChat = new ArrayList<>();

    private final Observer<SessionVO> sessionObserver = sessionVO -> {
        if (sessionVO != null){
            mensagemVO.setSessionVO(sessionVO);
        }
    };

    private final Observer<MensagemVO> mensagemObserver = new Observer<MensagemVO>() {
        @Override
        public void onChanged(MensagemVO mensagemVO) {
            Log.i("MENSAGEM", "onChanged: " + mensagemVO);
            mensagensChat.add(mensagemVO);
            mensagensAdapter.notifyDataSetChanged();
            recyclerMensagens.scrollToPosition(mensagensAdapter.getItemCount() - 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
        mensagensViewModel = new ViewModelProvider(this).get(MensagensViewModel.class);

        boxMensagem = findViewById(R.id.boxMensagem);
        recyclerMensagens = findViewById(R.id.recyclerMensagens);
        fabEnviar = findViewById(R.id.fabEnviar);

        //Configuração adapter
        mensagensAdapter = new MensagensAdapter(mensagensChat, getApplicationContext() );

        //Configuração recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMensagens.setLayoutManager( layoutManager );
        recyclerMensagens.setHasFixedSize( true );
        recyclerMensagens.setAdapter(mensagensAdapter);

        fabEnviar.setOnClickListener(view -> enviarMensagem(view));
    }

    @Override
    protected void onPause() {
        super.onPause();
        sessionViewModel.getSessionListMutableLiveData().removeObserver(sessionObserver);
        mensagensViewModel.getMensagensListLiveData().removeObserver(mensagemObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionViewModel.getSessionListMutableLiveData().observe(this, sessionObserver);
        mensagensViewModel.getMensagensListLiveData().observe(this, mensagemObserver);
    }


    public void enviarMensagem(View view){

        String textoMensagem = boxMensagem.getText().toString();
        boxMensagem.setText("");
        if ( !textoMensagem.isEmpty() ){

            MensagemVO mensagemUsuario = new MensagemVO();
            mensagemUsuario.setInput(textoMensagem);
            mensagemUsuario.setTipoUsuario(TipoUsuario.USUARIO);

            mensagensChat.add(mensagemUsuario);
            mensagensAdapter.notifyDataSetChanged();
            Log.i("MENSAGEM", "mensagem usuario: " + mensagemVO);
            mensagensViewModel.callMessageService(mensagemVO);
        }else {
            Toast.makeText(ChatActivity.this,
                    "Digite uma mensagem para enviar!",
                    Toast.LENGTH_LONG).show();
        }
    }


}
