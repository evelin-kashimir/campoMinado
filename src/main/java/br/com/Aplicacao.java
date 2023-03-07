package br.com;

import br.com.model.Tabuleiro;
import br.com.view.TabuleiroConsole;

public class Aplicacao {

  public static void main(String[] args) {

    Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
    new TabuleiroConsole(tabuleiro); //sem atribuir a alguma variável, apenas inicializando a classe;

  }
}
