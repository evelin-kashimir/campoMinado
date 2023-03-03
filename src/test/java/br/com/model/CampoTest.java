package br.com.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTest {
  private Campo campo;

  //Antes de exectar o teste, execute esta função
  @BeforeEach
  void iniciarCampo() {
    campo = new Campo(3, 3);
  }

  @Test
  void distanciaVizinho1EsquerdaTest() {
    Campo vizinhoEsquerda = new Campo(3, 2);
    boolean resultado = campo.addVizinho(vizinhoEsquerda);
    assertTrue(resultado);
  }

  @Test
  void distanciaVizinho1DireitaTest() {
    Campo vizinhoDireita = new Campo(3, 4);
    boolean resultado = campo.addVizinho(vizinhoDireita);
    assertTrue(resultado);
  }

  @Test
  void distanciaVizinho1EmCimaTest() {
    Campo vizinhoEmCima = new Campo(2, 3);
    boolean resultado = campo.addVizinho(vizinhoEmCima);
    assertTrue(resultado);
  }

  @Test
  void distanciaVizinho1EmbaixoTest() {
    Campo vizinhoEmbaixo = new Campo(4, 3);
    boolean resultado = campo.addVizinho(vizinhoEmbaixo);
    assertTrue(resultado);
  }

  @Test
  void distanciaVizinhoDiagonalTest() {
    Campo vizinhoDiagonal = new Campo(2, 2);
    boolean resultado = campo.addVizinho(vizinhoDiagonal);
    assertTrue(resultado);
  }

  @Test
  void vizinhoInvalidoTest() {
    Campo vizinhoInvalido = new Campo(5, 2);
    boolean resultado = campo.addVizinho(vizinhoInvalido);
    assertFalse(resultado);
  }
}
