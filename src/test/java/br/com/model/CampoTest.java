package br.com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.exception.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.w3c.dom.events.EventException;

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

  @Test
  void verificarCampoNaoMarcadoTest() {
    assertFalse(campo.isMarcado());
  }

  @Test
  void verificarCampoMarcadoTest(){
    campo.alternarMarcacao();
    assertTrue(campo.isMarcado());
  }

  @Test
  void alternarMarcacaoTest(){
    campo.alternarMarcacao();
    campo.alternarMarcacao();
    assertFalse(campo.isMarcado());
  }

  @Test
  void abrirCampoMarcadoTest(){
    campo.alternarMarcacao();
    boolean result = campo.abrir();
    assertFalse(result);
  }

  @Test
  void abrirCampoComExceptionTest() throws ExplosaoException {
    campo.setMinado(true);
    assertThrows(ExplosaoException.class, () -> campo.abrir());
  }

  @Test
  void abrirCampoSeguroTest() {
    assertTrue(campo.abrir());
  }

  @Test
  void objetivoAlcancadoProtegidoTest() {
    campo.setMinado(true);
    campo.setMarcado(true);
    assertTrue(campo.objetivoAlcancado());
  }

  @Test
  void objetivoAlcancadoDesvendado() {
    campo.setMinado(false);
    campo.setAberto(true);
    assertTrue(campo.objetivoAlcancado());
  }

  @Test
  void objetivoNaoAlcancado() {
    campo.setMinado(true);
    campo.setAberto(true);
    assertFalse(campo.objetivoAlcancado());
  }

  @Test
  void minasNaVizinhancaTest() {
    Campo vizinhoEmbaixo = new Campo(4, 3);
    vizinhoEmbaixo.setMinado(true);

    Campo vizinhoEmCima = new Campo(2, 3);
    vizinhoEmCima.setMinado(true);

    campo.addVizinho(vizinhoEmbaixo);
    campo.addVizinho(vizinhoEmCima);

    assertEquals(campo.minasNaVizinhanca(), 2L);
  }

  @Test
  void reiniciarJogoTest() {
    campo.reiniciar();
    assertFalse(campo.isMarcado());
    assertFalse(campo.isMinado());
    assertFalse(campo.isAberto());
  }

  @Test
  void toStringMarcadoTest() {
    campo.setMarcado(true);
    assertEquals(campo.toString(), "X");
  }

  @Test
  void toStringAbertoMinadoTest() {
    campo.setAberto(true);
    campo.setMinado(true);
    assertEquals(campo.toString(), "*");
  }

  @Test
  void toStringAbertoMaisQueZeroMinasTest() {
    Campo vizinhoEmbaixo = new Campo(4, 3);
    vizinhoEmbaixo.setMinado(true);
    campo.addVizinho(vizinhoEmbaixo);

    campo.setAberto(true);
    assertEquals(campo.toString(), "1");
  }

  @Test
  void toStringAbertoTest() {
    campo.setAberto(true);
    campo.setMinado(false);
    assertEquals(campo.toString(), " ");
  }

  @Test
  void toStringNaoAbertoOuMarcadoTest() {
    campo.setAberto(false);
    campo.setMinado(false);
    campo.setMarcado(false);
    assertEquals(campo.toString(), "?");
  }

  @Test
  void getsLinhaEColunaTest() {
    assertEquals(campo.getCOLUNA(), 3);
    assertEquals(campo.getLINHA(), 3);
  }
}
