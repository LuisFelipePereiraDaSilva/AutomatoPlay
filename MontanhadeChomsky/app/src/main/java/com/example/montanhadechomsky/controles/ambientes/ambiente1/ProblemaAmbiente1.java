package com.example.montanhadechomsky.controles.ambientes.ambiente1;

import com.example.montanhadechomsky.controles.ambientes.ProblemaAmbientes;
import com.example.montanhadechomsky.fachadas.Controler;

import java.io.Serializable;

public class ProblemaAmbiente1 extends ProblemaAmbientes implements Serializable {

    private ER expressao_relular;
    private String resposta_submetida = "";
    private String resposta_secundaria = "";

    public ProblemaAmbiente1(int numero, String problema, ER expressao_relular, String resposta_submetida, String resposta_secundaria) {
        super(numero, problema);
        this.expressao_relular = expressao_relular;
        this.resposta_submetida = resposta_submetida;
        this.resposta_secundaria = resposta_secundaria;
    }

    public ProblemaAmbiente1(int numero, String problema, ER expressao_relular) {
        super(numero, problema);
        this.expressao_relular = expressao_relular;
    }

    public ProblemaAmbiente1(int numero, String problema) {
        super(numero, problema);
    }

    public ProblemaAmbiente1() {
    }

    public ProblemaAmbiente1(int numero) {
        super(numero);
    }

    public void setThis(ProblemaAmbiente1 problema){
        this.expressao_relular = problema.expressao_relular;
        this.resposta_submetida = problema.resposta_submetida;
        this.resposta_secundaria = problema.resposta_secundaria;
        this.setThis(problema.getNumero(), problema.getStatus_resposta());
    }

    @Override
    public void setStatus_resposta(StatusResposta status_resposta) {
        super.setStatus_resposta(status_resposta);
        Controler.getControler().salvarProblemaAmbiente1(this);
    }

    public ER getExpressao_relular() {
        return expressao_relular;
    }

    public void setExpressao_relular(ER expressao_relular) {
        this.expressao_relular = expressao_relular;
    }

    public String getResposta_submetida() {
        return resposta_submetida;
    }

    public void setResposta_submetida(String resposta_submetida) {
        this.resposta_submetida = resposta_submetida;
        super.setStatus_resposta(StatusResposta.Correta);
        Controler.getControler().salvarProblemaAmbiente1(this);
    }

    public String getResposta_secundaria() {
        return resposta_secundaria;
    }

    public void setResposta_secundaria(String resposta_secundaria) {
        this.resposta_secundaria = resposta_secundaria;
        Controler.getControler().salvarProblemaAmbiente1(this);
    }
}
