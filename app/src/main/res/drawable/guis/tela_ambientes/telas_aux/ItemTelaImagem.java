package com.automatoplay.guis.tela_ambientes.telas_aux;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.automatoplay.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ItemTelaImagem extends Activity {

    private static String imagem = "";
    public static void setImagem(String img){
        imagem = img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_ambientes_imagem);

        PhotoView img = (PhotoView) findViewById(R.id.id_imagem);

        try {
            Resources resources = this.getResources();
            int resourceId = 0;

            resourceId = resources.getIdentifier(imagem.trim(), "drawable", this.getPackageName());

            img.setImageResource(resourceId);
        }catch (Exception e){
            img.setImageResource(R.drawable.imagem_nula);
        }


    }

    public void onStop(){
        super.onStop();
        finish();
    }

    public void clickTela(View view){
        onStop();
    }
}
