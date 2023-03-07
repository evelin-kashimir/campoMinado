package br.com;

import br.com.model.Tabuleiro;

public class Aplicacao {

  public static void main(String[] args) {

    Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

    tabuleiro.abrirCampo(3, 3);
    tabuleiro.marcarCampo(4, 4);
    tabuleiro.marcarCampo(4, 5);
    System.out.println(tabuleiro);
  }
}
