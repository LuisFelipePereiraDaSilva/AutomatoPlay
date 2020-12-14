package com.automatoplay.controles.ambientes.automatos.ambiente3.conversor.controler;

import com.automatoplay.controles.ambientes.automatos.ambiente2.AFD;
import com.automatoplay.controles.ambientes.automatos.ambiente3.conversor.model.Af;
import com.automatoplay.controles.ambientes.automatos.ambiente3.conversor.model.AfndLamb;
import com.automatoplay.controles.ambientes.automatos.ambiente3.conversor.model.Estado;
import com.automatoplay.controles.ambientes.automatos.ambiente3.conversor.model.Transicao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversor {
    public Af AfndLambToAfd (AfndLamb afndLamb) {
        Af afnd = this.afndLambToAfnd(afndLamb);
        Af afd = this.afndToAfd(afnd);
        return afd;
    }
    public Af afndToAfd (Af afnd) {
        String inicial = afnd.getEstadoInicial();
        Map<String, Estado> estados = new HashMap<String, Estado>();

        estados = this.GerarTabelaAfd(inicial, afnd, estados);
        //acessar inicial
        //caminhar pelas transições de acordo com a linguagem
        //pegar os estados novos que surgiram e jogar denro do Map
        //caminhar dentro deles
        Af afd = new Af();
        afd.setEstados(estados);
        return afd;
    }
    private Map<String, Estado> GerarTabelaAfd(String estadoNome, Af afnd, Map<String, Estado>estadosAfd) {
        String[] estadosCompoe = estadoNome.split(",");
        Estado estado = new Estado();
        //para cada entrada
        for(String entrada : afnd.getLinguagem()) {
            String[] caminhos = null;
            //pego os caminhos de cada estado que compõe o estado do AFD
            for(String estadoAtual : estadosCompoe) {
                if(afnd.getEstados().get(estadoAtual) != null) {
                    if (afnd.getEstados().get(estadoAtual).getTransicao().get(entrada) != null) {
                        caminhos = Utils.concatenarArray(caminhos, afnd.getEstados().get(estadoAtual).getTransicao().get(entrada).getCaminhos());
                        Transicao aux = new Transicao();
                        aux.setCaminhos(caminhos);
                        aux = Utils.RemoverRepeticoes(aux);
                        String estadoNome2 = Utils.arrayToString(aux.getCaminhos());
                        if (!estadosAfd.containsKey(estadoNome2)) {
                            estadosAfd.put(estadoNome2, null);
                            GerarTabelaAfd(estadoNome2, afnd, estadosAfd);
                        }
                        caminhos = aux.getCaminhos();
                    }
                }
            }
            //Crio uma transicao
            Transicao transicao = new Transicao();
            transicao.setCaminhos(caminhos);
            transicao = Utils.RemoverRepeticoes(transicao);
            estado.addTransicao(transicao, entrada);
            estadosAfd.put(estadoNome, estado);
        }
        return estadosAfd;
    }
    public Af afndLambToAfnd (AfndLamb afndLamb) {
        //procurar um estado
        for (Map.Entry<String, Estado> a : afndLamb.getEstados().entrySet()) {
            if (a.getValue() != null) {
                //recupero cada transição que existe e acrescento o fecho lambda de cada destino de transicao
                for(String leitura : afndLamb.getLinguagem()) {
                    Transicao transicao = a.getValue().getTransicao().get(leitura);
                    Transicao aux = this.completarTransicaoLambdaAfnd(transicao, afndLamb);
                    afndLamb.getEstados().get(a.getKey()).addTransicao(aux, leitura);
                    aux = this.completarTransicao(transicao, afndLamb, a, leitura);
                    afndLamb.getEstados().get(a.getKey()).addTransicao(aux, leitura);
                }
            }

        }
        Af afnd = afndLamb;
        return afnd;
        //ver onde suas transições o levam
        //acrescentar o fecho lambda do destino no estado inicial
        //ver se o estado inicial possui transição lambda
        //ver se o destino da transicao lambda possui transição com a enrada escolhida
        //fazer isso para o restante dos estados
    }
    private Transicao completarTransicao(Transicao transicao, AfndLamb afndLamb, Map.Entry<String, Estado> a, String leitura) {
        //navega nos lambdas procurando transição para a entrada referida
        Map<String, Transicao> fecho = afndLamb.getFechoLambda();
        String[] conjuntoTransicoes = null;
        if (transicao != null) {
            conjuntoTransicoes = transicao.getCaminhos();
        }
        if (fecho.get(a.getKey()) != null) {
            for(String lambda : fecho.get(a.getKey()).getCaminhos()) {
                if (lambda != null) {
                    Transicao caminhosAntigos = null;
                    if (afndLamb.getEstados().get(lambda) != null)
                        caminhosAntigos = afndLamb.getEstados().get(lambda).getTransicao().get(leitura);
                    if (caminhosAntigos != null) {
                        conjuntoTransicoes = Utils.concatenarArray(conjuntoTransicoes, caminhosAntigos.getCaminhos());
                    }
                }
            }
        }
        Transicao aux = new Transicao();
        aux.setCaminhos(conjuntoTransicoes);
        aux = Utils.RemoverRepeticoes(aux);
        return aux;
    }
    private Transicao completarTransicaoLambdaAfnd(Transicao transicao, AfndLamb afndLamb) {
        if (transicao != null) {
            String[] conjuntoTransicoes = transicao.getCaminhos();
            for(String str : transicao.getCaminhos()) {
                if (afndLamb.getFechoLambda() != null && afndLamb.getFechoLambda().get(str) != null)
                    conjuntoTransicoes = Utils.concatenarArray(conjuntoTransicoes, afndLamb.getFechoLambda().get(str).getCaminhos());
            }
            Transicao aux = new Transicao();
            aux.setCaminhos(conjuntoTransicoes);
            aux = Utils.RemoverRepeticoes(aux);

            return aux;
        }
        return null;
    }

    public AfndLamb tratarDados(String estados, String linguagem, List<String> transicoes, String estadoInicial, String estadoFinal) {
		//Preparar estados
        estados = estados.substring(1, estados.length()-2); //elimina os parênteses
        String[] estadosAux = estados.split(","); //separa os estados
        Map<String, Estado> estadosAF = new HashMap<String, Estado>(); //cria mapa de estados/
        for(int i = 0; i < estadosAux.length; i++) {
            estadosAF.put(estadosAux[i], null);
        }

		//Preparar linguagem/
        linguagem = linguagem.substring(1, linguagem.length()-2);
        String[] ling = linguagem.split(",");

		//Preparar estado inicial/
                estadoInicial = estadoInicial.substring(0, estadoInicial.length()-1);

		//Preparar estado final/
        estadoFinal = estadoFinal.substring(1,estadoFinal.length()-1);
        String[] estFinal = estadoFinal.split(",");

		//Preparar transicoes/
        String[][] transAux = new String[transicoes.size()][2]; 	//cria uma matriz de Strings, onde cada linha
        for(int i = 0; i < transicoes.size(); i++) {		//será uma transição nao tratada.
            transAux[i] = transicoes.get(i).split("->");
            transAux[i][0] = transAux[i][0].substring(1, transAux[i][0].length()-1); //remove os parenteses
            if (i != transicoes.size()-1)
                transAux[i][1] = transAux[i][1].substring(1, transAux[i][1].length()-2);	//remove as chaves e a virgula final
            else
                transAux[i][1] = transAux[i][1].substring(1, transAux[i][1].length()-1);	//remove apenas as chaves pq é o último elemento
        }

		//caminhar pelo transAux e ir jogando dentro do estadosAF/
        for(int i = 0; i < transicoes.size(); i++) {
            String[] funcaoTransicao = transAux[i][0].split(","); //separa estado e entrada da função de transição do AF
            Transicao transicaoAux = new Transicao();

            String[] caminhos = transAux[i][1].split(","); //separa os caminhos da transicao
            transicaoAux.setCaminhos(caminhos);//insere os caminhos

            Estado estadoAuxiliar = estadosAF.get(funcaoTransicao[0]); //busca o estado em questão

            if (estadoAuxiliar == null) { //verifica se é o primeiro valor a ser inserido na posição
                estadoAuxiliar = new Estado();
            }

            if (funcaoTransicao[0].equals(estadoInicial)) {
                estadoAuxiliar.setInicial(true);
            } else {
                estadoAuxiliar.setInicial(false);
            }
            estadoAuxiliar.setFinal(Utils.Exists(estFinal, funcaoTransicao[0])); //verifica se existe é final e seta

            estadoAuxiliar.addTransicao(transicaoAux, funcaoTransicao[1]); //adiciona transição no estado com a referida entrada

            estadosAF.put(funcaoTransicao[0], estadoAuxiliar); //adiciona estado na coleção de estados
        }

        for(int i = 0; i < estadosAux.length;i++){
            if(estadosAF.get(estadosAux[i]) == null){
                Estado teste = new Estado();
                teste.setInicial(false);
                if(Utils.Exists(estFinal,estadosAux[i])){
                    teste.setFinal(true);
                }else {
                    teste.setFinal(false);
                }
                //estadosAF.values();
                estadosAF.put(estadosAux[i],teste);
            }
        }

        AfndLamb afndLamb = new AfndLamb(estadosAF, ling);
        afndLamb.setFechoLambda();
        return afndLamb;
    }
    private AFD escreverAfd(AfndLamb afndLamb, Af afd) {
        //System.out.println(afndLamb.getLinguagem());

        //System.out.println(afd.getEstados());
        String transicaoAux = "";
        String transicaoString = "";
        String estadosString = "";
        String estadosAux = "";
        String nomeEstadoAFND = "";
        String nomeEstadoAF = "";
        for(Map.Entry<String,Estado> auxAFND : afndLamb.getEstados().entrySet()){
            nomeEstadoAFND = auxAFND.getKey();
            if(auxAFND.getValue().isInicial()){
                for (Map.Entry<String,Estado> auxAF : afd.getEstados().entrySet()){
                    nomeEstadoAF = auxAF.getKey();
                    if(nomeEstadoAF.equals(nomeEstadoAFND)){
                        auxAF.getValue().setInicial(true);
                    }
                }
            }
            if (auxAFND.getValue().isFinal()){
                for (Map.Entry<String,Estado> auxAF : afd.getEstados().entrySet()){
                    nomeEstadoAF = auxAF.getKey();
                    if(nomeEstadoAF.contains(nomeEstadoAFND)){
                        auxAF.getValue().setFinal(true);
                    }
                }
            }
        }
        for(Map.Entry<String, Estado> loop : afd.getEstados().entrySet()) {
            estadosAux = loop.getKey();
            if(loop.getValue().isInicial()){
                estadosAux = estadosAux + "_true";
            }else{
                estadosAux = estadosAux + "_false";
            }
            if(loop.getValue().isFinal()){
                estadosAux = estadosAux + "_true";
            }else{
                estadosAux = estadosAux + "_false";
            }
            if(estadosString.length() == 0){
                estadosString = estadosString + estadosAux;
            }else {
                estadosString = estadosString +" & "+ estadosAux;

            }
            estadosAux = "";

            //System.out.println("<"+loop.getKey()+">");
            for (String entrada : afndLamb.getLinguagem()) {
                if (loop.getValue().getTransicao().get(entrada) == null) {
                    //System.out.println("\t\t\t\t\t\t " + "-" + " ");
                } else {
                    transicaoAux = loop.getKey()+"_"+entrada+"_"+Utils.arrayToString(loop.getValue().getTransicao().get(entrada).getCaminhos());
                    //System.out.println(transicaoAux);
                    ///System.out.println(entrada+"\t\t\t\t\t\t<" + Utils.arrayToString(loop.getValue().getTransicao().get(entrada).getCaminhos()) + ">");

                }
                if (transicaoString.length() == 0){
                    if(transicaoAux.length() != 0) {
                        transicaoString = transicaoString + transicaoAux;
                    }
                }else {
                    if (transicaoAux.length() != 0) {
                        transicaoString = transicaoString + " & " + transicaoAux;
                    }
                }
                transicaoAux = "";
            }
        }
        String[] alfabeto = new String[afndLamb.getLinguagem().size()];
        for (int i = 0; i < afndLamb.getLinguagem().size(); i++)
            alfabeto[i] = afndLamb.getLinguagem().get(i);

        System.out.println(alfabeto);
        System.out.println(estadosString);
        System.out.println(transicaoString);

        AFD afdResultado = new AFD(estadosString, transicaoString, alfabeto);
        return afdResultado;
    }

    public static AFD converterAfndParaAfd(String estados, String linguagem, List<String> transicoes, String estadoInicial, String estadoFinal) {
        Conversor conversor = new Conversor();
        AfndLamb afndLamb = conversor.tratarDados(estados, linguagem, transicoes, estadoInicial, estadoFinal);
//        for (int i = 0; i < afndLamb.getEstados().size(); i++) {
//            if (afndLamb.getEstados().get(i) == null) {
//                return null;
//            }
//        }
        Af afnd = conversor.afndLambToAfnd(afndLamb);
        Af afd = conversor.afndToAfd(afnd);
        AFD afdResult = conversor.escreverAfd(afndLamb, afd);
        return afdResult;
    }
}
