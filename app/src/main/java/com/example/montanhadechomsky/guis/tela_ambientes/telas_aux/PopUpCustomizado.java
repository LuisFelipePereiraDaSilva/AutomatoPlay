package com.example.montanhadechomsky.guis.tela_ambientes.telas_aux;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.montanhadechomsky.R;
import com.example.montanhadechomsky.fachadas.Controler;
import com.example.montanhadechomsky.fachadas.GUI;

public class PopUpCustomizado extends Activity {

    private static String titulo;
    private static String texto;

    public PopUpCustomizado() {}

    public PopUpCustomizado(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_customizado);

        TextView t = findViewById(R.id.texto_pop_up);
        GUI.getGui().getItensMetodosAuxiliares().montagemTextView(this.texto != null ? this.texto : "Vazio", t, this);
        TextView t2 = findViewById(R.id.titulo_pop_up);
        t2.setText(this.titulo != null ? this.titulo : "Problemaaaa");
    }

    public void onStop(){
        super.onStop();
        finish();
    }

    public void clickBotaoClose(View view) {
        finish();
    }
}
