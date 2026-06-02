package thread;

import java.util.List;

import factory.ProcessadorCSVFactory;
import factory.ProcessorFactory;
import model.Venda;
import service.VendaProcessor;
import singleton.RelatorioSingleton;

public class VendaThread extends Thread {

  private final List<Venda> bloco;

  private final String tipoProcessador;

  public VendaThread(List<Venda> bloco, String tipoProcessador) {
    this.bloco = bloco;
    this.tipoProcessador = tipoProcessador;
  }

  public VendaThread(List<Venda> bloco) {
    this(bloco, "padrao");
  }

  public void run() {
    System.out.println("[" + Thread.currentThread().getName() + "] Iniciando processamento de "
        + bloco.size() + " registros...");

    try {
        // Instancia a factory concreta e usa o Factory Method corretamente
        ProcessorFactory factory = new ProcessadorCSVFactory();
        VendaProcessor processador = factory.criarProcessador();

        processador.processar(bloco);

        RelatorioSingleton relatorio = RelatorioSingleton.getInstance();
        relatorio.acumularResultados(0, 0, tipoProcessador, 0);

        System.out.println("[" + Thread.currentThread().getName() + "] Bloco processado e gravado no relatorio.");

    } catch (Exception e) {
        System.err.println("[" + Thread.currentThread().getName() + "] Erro ao processar bloco: " + e.getMessage());
        e.printStackTrace();
    }
}

}