package br.com.view;

import br.com.exception.ExplosaoException;
import br.com.exception.SairException;
import br.com.model.Tabuleiro;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
  private Tabuleiro tabuleiro;
  private Scanner entrada = new Scanner(System.in);

  public TabuleiroConsole(Tabuleiro tabuleiro) {
    this.tabuleiro = tabuleiro;

    executarJogo();
  }

  private void executarJogo() {

    try {
      boolean continuar = true;

      while (continuar) {
        cicloDoJogo();

        System.out.println("Jogar novamente? (S/n) ");
        String resposta = entrada.nextLine();

        if("n".equalsIgnoreCase(resposta)) {
          continuar = false;
        } else {
          tabuleiro.reinicializarJogo();
        }
      }

    } catch (SairException se) {
      System.out.println("Até a próxima!");
    } finally {
      entrada.close();
    }

  }

  private void cicloDoJogo() {

    /*
    * 1 - Enquanto o objetivo não foi alcançado, entre no loop;
    * 2 - Imprime o tabuleiro no console;
    * 3 - Captura o valor de x e y digitado no console;
    * 4 - Com uma interface funcional, transforma a string em inteiro ignorando os espaços e por fim
    *     iterando sobre cada numero;
    * 5 - Captura novamente a opção digitada no console, se for diferente de sair segue para o switch;
    * 6 - Se o valor digitado for 1, executa a função de abrir campos;
    *     Se for 2, executa a função para marcar o campo desejado;
    * 7 - Caso o valor digitado seja "sair" é lançada uma exceção e o usuário recebe uma mensagem,
    *     de interrupção do sistema;
    */
    try {
      while(!tabuleiro.objetivoAlcancado()) {
        System.out.println(tabuleiro);
        String digitado = capturarValorDigitado("Digite (x, y): ");

        Iterator<Integer> xy = Arrays.stream(digitado.split(","))
            .map(s -> Integer.parseInt(s.trim())).iterator();

        digitado = capturarValorDigitado("[1] - ABRIR  | [2] - (DES)MARCAR: ");

        switch (digitado) {
          case "1" : tabuleiro.abrirCampo(xy.next(), xy.next());
          break;
          case "2" : tabuleiro.marcarCampo(xy.next(), xy.next());
        }
      }
      System.out.println("Você Gahou!");
    } catch (ExplosaoException ee) {
      System.out.println(tabuleiro);
      System.out.println("BOOOOOOOM!!!!!!");
      System.out.println("Você perdeu!");
    }
  }

  private String capturarValorDigitado(String texto) {
    System.out.print(texto);
    String digitado = entrada.nextLine();

    if("sair".equalsIgnoreCase(digitado)) {
      throw new SairException();
    }
    return digitado;
  }
}
