package br.com.padawan.botjs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.padawan.botjs.R;
import br.com.padawan.botjs.vo.MensagemVO;
import br.com.padawan.botjs.vo.TipoUsuario;

public class MensagensAdapter extends RecyclerView.Adapter<MensagensAdapter.MyViewHolder> {

    private List<MensagemVO> mensagens;
    private Context context;

    private static final int TIPO_REMETENTE = 0;
    private static final int TIPO_CHATBOT = 1;

    public MensagensAdapter(List<MensagemVO> lista, Context context) {
        this.mensagens = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if (viewType == TIPO_REMETENTE) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_mensagem_chat_remetente, parent, false);
        } else if (viewType == TIPO_CHATBOT) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_mensagem_chat_bot, parent, false);
        }
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MensagemVO mensagem = (MensagemVO) mensagens.get(position);
        String msg = "";

        if (mensagem.getTipoUsuario() == TipoUsuario.USUARIO) {
            msg = mensagem.getInput();

        } else {
            msg = mensagem.getOutput();
        }

        holder.textoMensagem.setText(msg);
        holder.textoNome.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    @Override
    public int getItemViewType(int position) {

        MensagemVO mensagem = (MensagemVO) mensagens.get(position);

        if (mensagem.getTipoUsuario() == TipoUsuario.USUARIO) {
            return TIPO_REMETENTE;
        }

        return TIPO_CHATBOT;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textoNome;
        TextView textoMensagem;

        public MyViewHolder(View itemView) {
            super(itemView);

            textoNome = itemView.findViewById(R.id.textoNome);
            textoMensagem = itemView.findViewById(R.id.textoMensagem);
        }
    }

}
