package com.automatoplay.guis.tela_ambientes.telas_aux;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.automatoplay.R;
import com.automatoplay.fachadas.GUI;

public class PopUpCustomizado extends Activity {

    private static String titulo;
    private static String texto;
    private static String link;

    public PopUpCustomizado() {}

    public PopUpCustomizado(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public PopUpCustomizado(String titulo, String texto, String link) {
        this.titulo = titulo;
        this.texto = texto;
        this.link = link;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_customizado);

        TextView t = findViewById(R.id.texto_pop_up);
        GUI.getGui().getItensMetodosAuxiliares().montagemTextView(this.texto != null ? this.texto : "Vazio", t, this);
        TextView t2 = findViewById(R.id.titulo_pop_up);
        t2.setText(this.titulo != null ? this.titulo : "Problema");

        if (link != null && !link.equals("")) {
            TextView linha = findViewById(R.id.linha_pop_up);
            linha.setVisibility(View.VISIBLE);
            TextView link = findViewById(R.id.link_pop_up);
            link.setVisibility(View.VISIBLE);
        }
    }

    public void onStop(){
        super.onStop();
        finish();
    }

    public void clickBotaoClose(View view) {
        finish();
    }

    public void clickLink(View view) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        this.startActivity(intent);
    }
}
