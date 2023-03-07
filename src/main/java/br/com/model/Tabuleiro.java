package br.com.model;

import br.com.exception.ExplosaoException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
  private int linhas;
  private int colunas;
  private int minas;

  private final List<Campo> campos = new ArrayList<>();

  public Tabuleiro(int linhas, int colunas, int minas) {
    this.linhas = linhas;
    this.colunas = colunas;
    this.minas = minas;

    gerarCampos();
    assossiarVizinhos();
    distribuirMinas();
  }

  public void abrirCampo(int linha, int coluna) {
    try {
      campos.stream()
          .filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
          .findFirst().ifPresent(Campo::abrir); //trás o primeiro e se for presente, executa a função abrir
    } catch (ExplosaoException ee) {
      campos.forEach(campo -> campo.setAberto(true)); //iterando sobre todos os campos e revelando todos os valores
      throw ee; //lançando exceção para a próxima classe
    }
  }

  public void marcarCampo(int linha, int coluna) {
    campos.stream()
        .filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
        .findFirst().ifPresent(Campo::alternarMarcacao); //trás o primeiro e se for presente, executa a função marcar
  }

  //Cria uma matriz de Campos
  private void gerarCampos() {
    for(int linha = 0; linha < linhas; linha++) {
      for(int coluna = 0; coluna < colunas; coluna++) {
        campos.add(new Campo(linha, coluna));
      }
    }
  }

  //Verifica utilizando o método criado na classe Campo os inidices iterados se são considerados
  //vizinhos ou não
  private void assossiarVizinhos() {
    for (Campo c1: campos) {
      for (Campo c2: campos) {
        c1.addVizinho(c2);
      }
    }
  }

  //Enquanto o número de minas criadas for menor que o que foi especificado na criação do objeto,
  //continuará criando minas.
  private void distribuirMinas() {
    long minasArmadas = 0;
    Predicate<Campo> minado = Campo::isMinado; //verificando todos os campos com o atributo minado true;

    do {
      int aleatorio = (int) (Math.random() * campos.size()); //gerando um valor randomico baseado no tamanho da lista
      campos.get(aleatorio).setMinado(true);
      minasArmadas = campos.stream().filter(minado).count();
    } while (minasArmadas < minas);
  }

  //Todos os campos estão com objetivo alcançado, baseado no método criado na classe Campo
  public boolean objetivoAlcancado() {
    return campos.stream().allMatch(Campo::objetivoAlcancado);
  }

  public void reinicializarJogo() {
    campos.stream().forEach(Campo::reiniciar);
    distribuirMinas();
  }

  @Override
  public String toString() {
    //Quando houver uma necessidade grande de concatenação, a melhor opção é usar a classe stringBuilder
    StringBuilder sb = new StringBuilder();
    int i = 0;

    for(int linha = 0; linha < linhas; linha++){
      for(int coluna = 0; coluna < colunas; coluna++){
        sb.append(" ");
        sb.append(campos.get(i)); //imprime o valor contente na posição do indice especificado
        sb.append(" ");
        i++;
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
