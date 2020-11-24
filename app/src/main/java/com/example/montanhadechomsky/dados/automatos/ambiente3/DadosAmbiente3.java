package com.example.montanhadechomsky.dados.automatos.ambiente3;

import android.content.Context;
import android.widget.Toast;

import com.example.montanhadechomsky.controles.ambientes.automatos.ambiente3.ProblemaAmbiente3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DadosAmbiente3 {

    private Context context;

    public DadosAmbiente3(Context context){
        this.context = context;
    }

    public void limparArquivo(String arquivo) {
        try {
            FileOutputStream file;
            file = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(file);
            try {
                os.close();
            }catch (EOFException e){}
        }
        catch(Exception e){
            mensagemExibir();
        }
    }

    public void salvar(String arquivo, ProblemaAmbiente3 problema){
        try{
            ArrayList<ProblemaAmbiente3> questoes = ler(arquivo);
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

    public ArrayList<ProblemaAmbiente3> ler(String arquivo){
        ArrayList<ProblemaAmbiente3> dados = new ArrayList<ProblemaAmbiente3>();
        try{
            FileInputStream file;
            file = context.openFileInput(arquivo);

            try{
                ObjectInputStream is = new ObjectInputStream(file);
                ProblemaAmbiente3 questao;
                while((questao = (ProblemaAmbiente3) is.readObject()) != null){
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

    public ProblemaAmbiente3 getProblema(String arquivo, int numero){
        ProblemaAmbiente3 problema = null;
        try{
            FileInputStream file;
            file = context.openFileInput(arquivo);

            try{
                ObjectInputStream is = new ObjectInputStream(file);
                while((problema = (ProblemaAmbiente3) is.readObject()) != null){
                    if(problema.getNumero() == numero)
                        break;
                    else
                        problema = null;
                }
                is.close();
            }catch(EOFException e){ }
            catch(Exception e){
                limparArquivo(arquivo);
                mensagemExibir();
            }
        }catch(FileNotFoundException e){
        }
        return problema;
    }

    public void mensagemExibir(){
        Toast.makeText(context, "Erro no arquivo de questões do Ambiente 3, por favor verifique o espaço livre no seu dispositivo",
                Toast.LENGTH_LONG).show();
    }
}
