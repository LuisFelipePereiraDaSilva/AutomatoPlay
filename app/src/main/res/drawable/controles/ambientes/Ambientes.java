package com.automatoplay.controles.ambientes;

public class Ambientes {

    public String getInstrucoesAmbiente2E3(){
        return"Para construir o autômato, você deve utilizar o ambiente de resposta proposto. Para criar um estado," +
                " basta clicar em um ponto da tela, com isso, irá aparecer as opções do estado (Ex: se é estado" +
                " de aceitação ou inicial), caso não marque nenhuma opção, por padrão, o estado nem é de aceitação e" +
                " nem é estado inicial. Caso queira modificar ou deletar algum estado, basta segurar o click em cima" +
                " do estado que você deseja modificar ou deletar, que irá aparecer as informações do estado selecionado." +
                " Para realizar um transição, basta dá um click nos dois estados desejados (Pode ser o mesmo estado), feito isso," +
                " se escolhe os símbolos da transição e confirma a mesma. Para modificar ou deletar uma transição, basta segurar o click no simbolo da" +
                " transição ou selecionar os dois estados da transição clicando sobre eles, com isso, você pode modificar os símbolos da transição ou" +
                " deletar a transição. Se você selecionou um estado, mas quer desfazer a ação, basta clicar na seta voltar no menu de baixo.";
    }

    public String getAjudaAmbiente2E3(){
        return "&-> Menu de Rodapé:& Você pode visualizar/ocultar o menu do radapé clicando no botão de cor preta com a 'Seta' para expandi-lo/minimiza-lo.\n\n" +

                "&-> Pergunta:& Você pode visualizar a pergunta clicando no botão com o símbolo de '?' no menu abaixo, ou clicando no botão" +
                " com o símbolo dos 3 pontos no menu abaixo e selecionando 'Pergunta' no pop-up que será aberto.\n\n" +

                "&-> Instruções:& Você pode visualizar as instruções clicando no botão" +
                " com o símbolo dos 3 pontos no menu abaixo e selecionando 'Instruções' no pop-up que será aberto.\n\n" +

                "&-> Links:& Ao click em um link será visualizado uma imagem que foi destino ao mesmo.\n\n" +

                "&-> Botão Ajuda:& Você pode visualizar as instruções de ajuda clicando no botão" +
                " com o símbolo dos 3 pontos no menu abaixo e selecionando 'Ajuda' no pop-up que será aberto.\n\n" +

                "&-> Botão Resposta:& Você pode visualizar a resposta da questão depois de acerta-lá clicando no botão" +
                " com o símbolo dos 3 pontos no menu abaixo e selecionando 'Resposta' no pop-up que será aberto.\n\n" +

                "&-> Botão Reiniciar:& Ao clicar no botão com o símbolo de 'Vasoura' no menu abaixo, a resposta que foi digitada para a questão será apagada.\n\n" +

                "&-> Botão Voltar:& Ao clicar no botão com o símbolo de 'Voltar' no menu abaixo, o aplicativo voltará para as questões.\n\n" +

                "&-> Botão Tabela:& Você pode visualizar a tabela de transição do autômato que está sendo" +
                " construído clicando no botão" +
                " com o símbolo dos 3 pontos no menu abaixo e selecionando 'Tabela' no pop-up que será aberto.\n\n" +

                "&-> Botão Salvar:& Ao clicar no botão com o símbolo de 'Salvar' no menu abaixo, a resposta será submetida para resolução e retornará o resultado se você" +
                " acertou ou errou a resposta da questão.";
    }
}
