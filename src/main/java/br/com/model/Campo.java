package br.com.model;

import br.com.exception.ExplosaoException;
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
  void alternarMarcacao() {
    if(!aberto) {
      this.marcado = !this.marcado;
    }
  }

  boolean abrir() {
    if(!this.marcado && !this.aberto) {
      this.aberto = true;

      //Se o campo clicado estiver minado, lança uma exceção e o sistema é interrompido, podendo ser
      //reiniciado.
      if(this.minado) {
        throw new ExplosaoException();
      }

      //Se ao redor estiver seguro, chama o método abrir para cada item da lista.
      if(vizinhancaSegura()) {
        vizinhos.forEach(Campo::abrir);
      }
      return true;
    } else {
      return false;
    }
  }

  boolean vizinhancaSegura() {
    /*
      Utilizando stream e um predicado dentro do noneMatch para verificar que nenhum vizinho da
      lista tem o atributo minado sendo true. Caso esteja false, ele adiciona ao novo stream e
      retorna essa nova lista de itens com o atributo minado false.
    */
    return vizinhos.stream().noneMatch(v -> v.minado);
  }

  public boolean isMarcado(){
    return this.marcado;
  }

  public boolean isAberto(){
    return this.aberto;
  }

  public boolean isMinado(){
    return this.minado;
  }

  public void setMarcado(boolean marcado) {
    this.marcado = marcado;
  }

  public void setMinado(boolean minado) {
    this.minado = minado;
  }

  public void setAberto(boolean aberto) {
    this.aberto = aberto;
  }

  public int getCOLUNA() {
    return COLUNA;
  }

  public int getLINHA() {
    return LINHA;
  }

  //Se o campo está marcado e minado ou não minado e aberto, o objetivo foi alcançado,
  //Para verificar se o objetivo do jogo foi alcançado, deverá ser levado em consideração todos os
  //campos com o  objetivo alcançado.
  boolean objetivoAlcancado() {
    boolean desvendado = !minado && aberto;
    boolean protegido = minado && marcado;
    return desvendado || protegido;
  }

  //Retorna a qtd de minas no jogo
  long minasNaVizinhanca() {
    return vizinhos.stream().filter(v -> v.minado).count();
  }

  void reiniciar() {
    this.aberto = false;
    this.marcado = false;
    this.minado = false;
  }

  @Override
  public String toString() {
    if(marcado) {
      return "X";
    } else if(aberto && minado) {
      return "*";
    } else if(aberto && minasNaVizinhanca() > 0) {
      return Long.toString(minasNaVizinhanca());
    } else if(aberto) {
      return " ";
    } else {
      return "?";
    }
  }
}
