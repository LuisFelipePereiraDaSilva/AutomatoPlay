package com.example.montanhadechomsky.dados.automatos.ambiente2;

import android.content.Context;
import android.widget.Toast;

import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente2.ProblemaAmbiente2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DadosAmbiente2 {

    private Context context;

    public DadosAmbiente2(Context context){
        this.context = context;
    }

    public void salvar(String arquivo, ProblemaAmbiente2 problema){
        try{
            ArrayList<ProblemaAmbiente2> questoes = ler(arquivo);
            FileOutputStream file;
            file = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(file);

            int i;
            for(i = 0; i < questoes.size(); i++) {
                if (problema.getNumero() == questoes.get(i).getNumero())
                    break;
            }
            if(i == questoes.size())
                questoes.add(problema);
            else
                questoes.get(i).setThis(problema);

            for(i = 0; i < questoes.size(); i++){
                os.writeObject(questoes.get(i));
            }
            try {
                os.close();
            }catch (EOFException e){}
        }
        catch(Exception e){
            mensagemExibir();
        }
    }

    public void remover(String arquivo, ProblemaAmbiente2 problema){

    }

    public ArrayList<ProblemaAmbiente2> ler(String arquivo){
        ArrayList<ProblemaAmbiente2> dados = new ArrayList<ProblemaAmbiente2>();
        try{
            FileInputStream file;
            file = context.openFileInput(arquivo);

            try{
                ObjectInputStream is = new ObjectInputStream(file);
                ProblemaAmbiente2 questao;
                while((questao = (ProblemaAmbiente2) is.readObject()) != null){
                    dados.add(questao);
                }
                is.close();
            }catch(EOFException e){ }
            catch(Exception e){
                mensagemExibir();
            }
        }catch(FileNotFoundException e){
        }
        return dados;
    }

    public ProblemaAmbiente2 getProblema(String arquivo, int numero){
        ProblemaAmbiente2 problema = null;
        try{
            FileInputStream file;
            file = context.openFileInput(arquivo);

            try{
                ObjectInputStream is = new ObjectInputStream(file);
                while((problema = (ProblemaAmbiente2) is.readObject()) != null){
                    if(problema.getNumero() == numero)
                        break;
                    else
                        problema = null;
                }
                is.close();
            }catch(EOFException e){ }
             catch(Exception e){
                mensagemExibir();
            }
        }catch(FileNotFoundException e){
        }

        return problema;
    }

    public void mensagemExibir(){
        Toast.makeText(context, "Erro no arquivo de questões do Ambiente 2, por favor verifique o espaço livre no seu dispositivo",
                Toast.LENGTH_LONG).show();
    }
}
