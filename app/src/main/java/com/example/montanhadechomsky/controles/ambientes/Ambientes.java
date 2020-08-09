package com.example.montanhadechomsky.controles.ambientes;

public class Ambientes {

    public String getInstrucoesAmbiente2E3(){
        return"Para contruir o autômato, você deve utilizar o ambiente de resposta abaixo. Para criar um estado," +
                " basta clicar em um dos retângulos, com isso, irá aparecer as opções do estado (Ex: se é estado" +
                " de aceitação ou inicial), caso não marque nenhuma opção, por padrão, o estado nem é de aceitação e" +
                " nem é estado inicial. Caso queira modificar ou deletar algum estado, basta segurar o click em cima" +
                " do estado que você deseja modificar ou deletar, que irá aparecer as informações do estado selecionado." +
                " Para realizar um transição, basta dá um click nos dois estados desejados (Pode ser o mesmo estado), feito isso," +
                " se escolhe os símbolos da transição e confirma a mesma. Para modificar ou deletar uma transição, basta segurar o click no simbolo da" +
                " transição ou selecionar os dois estados da transição clicando sobre eles, com isso, você pode modificar os símbolos da transição ou" +
                " deletar a transição. Se você selecionou um estado, mas quer desfazer a ação, basta clicar na seta voltar no menu de baixo.";
    }

    public String getAjudaAmbiente2E3(){
        return "&-> Pergunta:& Você pode visualizar a pergunta no ambiente destinado a ela de forma pequena, como pode também segurar o" +
                " click em cima da pergunta para abrir ela em tela cheia.\n\n" +

                "&-> Instruções:& Você pode visualizar as instruções no ambiente destinado a ela de forma pequena, como pode também segurar o" +
                " click em cima das instruções para abrir elas em tela cheia.\n\n" +

                "&-> Links:& Ao click em um link será visualizado uma imagem que foi destino ao mesmo.\n\n" +

                "&-> Botão Ajuda:& Ao clicar no botão ajuda, irá abrir uma tela com instruções de uso do aplicativo.\n\n" +

                "&-> Botão Resposta:& Pode-se visualizar a resposta da questão depois de acerta-lá cliclando no botão resposta.\n\n" +

                "&-> Botão Reiniciar:& Ao clicar no botão reiniciar, a resposta que foi digitada para a questão será apagada.\n\n" +

                "&-> Botão Abadonar:& Ao clicar no botão abadonar, o aplicativo sairá da questão que foi selecionada.\n\n" +

                "&-> Botão Tabela:& Ao clicar no botão tabela, irá abrir uma tela com a tabela de transição do autômato que está sendo" +
                " construído.\n\n" +

                "&-> Resposta:& Logo após os botões, tem-se o ambiente para colocar a resposta da questão. Nesse ambiente, você irá fazer" +
                " o autômato da questão. Você pode crescer o bloco da resposta cliando no símbolo que fica em cima a direita dentro do ambiente" +
                " da resposta. Você pode também ajustar o zoom do bloco da resposta escolhendo uma opção de zoom em baixo a direita dentro do " +
                "ambiente da resposta.\n\n" +

                "&-> Botão Submeter:& Ao clicar no botão submeter, a resposta será submetida para resolução e retornará o resultado se você" +
                " acertou ou errou a resposta da questão.";
    }

    public String getInstrucoesAmbiente1(){
        return "Você deve construir as expressões regulares utilizando os operadores '+' para a operação de união, " +
                "'*' para o feijo de kleener, para concatenação basta deixar os símbolos colados, além dessas operações, você também pode fazer uso de parêntes '()'. É possível fazer uso de espaços " +
                "para a organização da ER, estes são desconsiderados na correção.";
    }

    public String getAjudaAmbiente1(){
        return "&-> Pergunta:& Você pode visualizar a pergunta no ambiente destinado a ela de forma pequena, como pode também segurar o" +
                " click em cima da pergunta para abrir ela em tela cheia.\n\n" +

                "&-> Instruções:& Você pode visualizar as instruções no ambiente destinado a ela de forma pequena, como pode também segurar o" +
                " click em cima das instruções para abrir elas em tela cheia.\n\n" +

                "&-> Links:& Ao click em um link será visualizado uma imagem que foi destino ao mesmo.\n\n" +

                "&-> Botão Ajuda:& Ao clicar no botão ajuda, irá abrir uma tela com instruções de uso do aplicativo.\n\n" +

                "&-> Botão Resposta:& Pode-se visualizar a resposta da questão depois de acerta-lá cliclando no botão resposta.\n\n" +

                "&-> Botão Reiniciar:& Ao clicar no botão reiniciar, a resposta que foi digitada para a questão será apagada.\n\n" +

                "&-> Botão Abadonar:& Ao clicar no botão abadonar, o aplicativo sairá da questão que foi selecionada.\n\n" +

                "&-> Resposta:& Logo após os botões, tem-se o ambiente para colocar a resposta da questão. Nesse ambiente, você irá digitar" +
                " a expressão regular da questão.\n\n" +

                "&-> Botão Submeter:& Ao clicar no botão submeter, a resposta será submetida para resolução e retornará o resultado se você" +
                " acertou ou errou a resposta da questão.";
    }

}
