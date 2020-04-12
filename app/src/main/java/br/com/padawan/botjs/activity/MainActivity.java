package br.com.padawan.botjs.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import br.com.padawan.botjs.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonStartChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.layout.toolbar);
        setSupportActionBar(toolbar);

        buttonStartChat = findViewById(R.id.buttonStartChat);


        buttonStartChat.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);

        });
    }
}
