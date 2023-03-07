package br.com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.exception.ExplosaoException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabuleiroTest {
  private Tabuleiro tabuleiro;

  @BeforeEach
  void tabuleiro () {
    tabuleiro = new Tabuleiro(6, 6, 6);
  }


  @Test
  void abrirCampoTest() throws ExplosaoException {
    try {
      tabuleiro.abrirCampo(1, 2);
    } catch (ExplosaoException ee) {
      assertThrows(ExplosaoException.class, () -> tabuleiro.abrirCampo(1, 2));
    }
  }

  @Test
  void marcarCampoTest() {
    tabuleiro.marcarCampo(1, 2);
    assertTrue(tabuleiro.getCampos().stream().anyMatch(Campo::isMarcado));
  }

  @Test
  void objetivoAlcancadoTest() {
    assertFalse(tabuleiro.objetivoAlcancado());
  }

  @Test
  void reinicializarJogoTest(){
    tabuleiro.reinicializarJogo();
    assertFalse(tabuleiro.getCampos().stream().anyMatch(Campo::isMarcado));
  }

  @Test
  void toStringTest() {
    assertNotNull( tabuleiro.toString());
  }
}
