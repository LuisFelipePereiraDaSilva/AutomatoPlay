package com.automatoplay.guis.tela_ambientes.telas_aux;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ItensMetodosAuxiliares {

    public Spannable linkandoImagens(Spannable spannableText, String texto_completo, final Context tela){ ;
        if(texto_completo.contains("(*#") && texto_completo.contains("#*)")) {
            String texto = texto_completo;
            ArrayList<ArrayList> array1 = new ArrayList();
            while (texto_completo.contains("(*#") && texto_completo.contains("#*)")) {
                int n1 = texto_completo.indexOf("(*#");
                int n2 = texto_completo.indexOf("#*)");
                texto_completo = texto_completo.substring(0, n1) + texto_completo.substring(n1+3, n2) + texto_completo.substring(n2+3);
                int w = texto_completo.length();
                final String nome = texto_completo.substring(n1, n2 - 3);

                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        ItemTelaImagem.setImagem(nome);
                        Intent in = new Intent(tela, ItemTelaImagem.class);
                        tela.startActivity(in);
                    }
                };

                ArrayList array2 = new ArrayList();
                array2.add(clickableSpan);
                array2.add(n1);
                array2.add(n2 - 3);
                array1.add(array2);

            }
            spannableText = new SpannableString(texto.replace("(*#", "").replace("#*)", ""));
            for(int i = 0; i < array1.size(); i++){
                ArrayList array2 = array1.get(i);
                spannableText.setSpan((ClickableSpan) array2.get(0), (int) array2.get(1), (int) array2.get(2), 1);
            }
        }
        return spannableText;
    }

    public TextView montagemTextView(String texto, String tipo, TextView t, final Context tela){
        String textoCompleto = tipo + texto;
        Spannable spannableText = new SpannableString(textoCompleto);
        spannableText = linkandoImagens(spannableText, textoCompleto, tela);
        int n = textoCompleto.indexOf(":") + 1;
        if(n > 0)
            spannableText.setSpan(new StyleSpan(Typeface.BOLD), 0, n, 0);

        t.setText(spannableText);
        t.setMovementMethod(LinkMovementMethod.getInstance());

        return t;
    }

    public TextView montagemTextView(String texto, TextView t, final Context tela){
        String textoCompleto = texto;
        ArrayList<String> textos_negrito = new ArrayList<>();
        String text = "";
        boolean ativou = false;
        for(int i = 0; i < textoCompleto.length(); i++){
            char c = textoCompleto.charAt(i);
            if(c != '&' && ativou)
                text += c;
            if(c == '&'){
                if(!ativou)
                    ativou = true;
                else {
                    ativou = false;
                    textos_negrito.add(text);
                    text = "";
                }
            }
        }

        textoCompleto = textoCompleto.replace("&", "");
        Spannable spannableText = new SpannableString(textoCompleto);
        spannableText = linkandoImagens(spannableText, textoCompleto, tela);

        for(int i = 0; i < textos_negrito.size(); i++) {
            int n = textoCompleto.indexOf(textos_negrito.get(i));
            int n2 = n + textos_negrito.get(i).length();
            if (n2 > 0)
                spannableText.setSpan(new StyleSpan(Typeface.BOLD), n, n2, 0);
        }

        spannableText.removeSpan('&');
        t.setText(spannableText);
        t.setMovementMethod(LinkMovementMethod.getInstance());

        return t;
    }

    public String montagemString(String texto, String tipo){
        String textoCompleto = tipo + texto;
        Spannable spannableText = new SpannableString(textoCompleto);
        int n = textoCompleto.indexOf(":") + 1;
        if(n > 0)
            spannableText.setSpan(new StyleSpan(Typeface.BOLD), 0, n, 0);

        return spannableText.toString();
    }
}
