package br.com.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {
  private final int LINHA;
  private final int COLUNA;

  private boolean minado = false;
  private boolean aberto = false;
  private boolean marcado = false;

  //Campos em volta do campo selecionado
  private List<Campo> vizinhos = new ArrayList<>();

  Campo(int linha, int coluna) {
    this.LINHA = linha;
    this.COLUNA = coluna;
  }

  boolean addVizinho(Campo vizinho){
    return true;
  }
}
