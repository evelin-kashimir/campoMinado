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
    //Verificando se o campo em que o vizinho está, é diferente do campo atual clicado com linha e coluna;
    boolean linhaDiferente = this.LINHA != vizinho.LINHA;
    boolean colunaDiferente = this.COLUNA != vizinho.COLUNA;

    //Se ambos forem diferentes, significa que o vizinho está na diagonal;
    boolean diagonal = linhaDiferente && colunaDiferente;

    //Verificando a diferença de casas distantes do vizinho para o campo atual clicado;
    int deltaLinha = Math.abs(this.LINHA - vizinho.LINHA);
    int deltaColuna = Math.abs(this.COLUNA - vizinho.COLUNA);
    int deltaGeral = deltaColuna + deltaLinha;

    if(deltaGeral == 1 && !diagonal) {
      vizinhos.add(vizinho);
      return true;
    } else if (deltaGeral == 2 && diagonal) {
      vizinhos.add(vizinho);
      return true;
    } else {
      return false;
    }
    /*
      Se a soma das diferenças entre os campos for igual a 1 e não estiver na diagonal, significa
      que o campo do parâmetro é vizinho direto do campo clicado, retornando true e adicionando a
      lista de vizinhos;

      Se a soma das diferenças entre os campos for igual a 2 e estiver na diagonal, significa que
      p parâmetro é vizinho da diagonal, sendo um campo válido também, retorna true e adiciona a
      lista de vizinhos;

      Caso contrário, retorna false e não adiciona nenhum vizinho a lista.
    */
  }
}
