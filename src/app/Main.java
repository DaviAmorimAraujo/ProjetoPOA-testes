package app;

import model.Venda;
import service.CSVReader;
import service.RelatorioService;
import thread.VendaThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do sistema de análise de vendas.
 *
 * Orquestra a leitura do CSV, a divisão dos registros entre threads
 * e a exibição do relatório final.
 */
public class Main {

    // Número de threads que irão processar os dados em paralelo
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) throws Exception {

        // 1. Leitura do arquivo CSV
        CSVReader reader = new CSVReader("src/resources/vendas.csv");
        List<Venda> vendas = reader.lerVendas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhum registro encontrado no arquivo CSV.");
            return;
        }

        // 2. Divisão dos registros em blocos para cada thread
        int tamBloco = (int) Math.ceil((double) vendas.size() / NUM_THREADS);
        List<VendaThread> threads = new ArrayList<>();

        for (int i = 0; i < NUM_THREADS; i++) {
            int inicio = i * tamBloco;
            int fim = Math.min(inicio + tamBloco, vendas.size());
            if (inicio >= vendas.size()) break;

            List<Venda> bloco = vendas.subList(inicio, fim);

            VendaThread t = new VendaThread(bloco, i + 1);
            threads.add(t);
            t.start();
        }

        // 3. Aguarda todas as threads finalizarem antes de gerar o relatório
        for (VendaThread t : threads) {
            t.join();
        }

        // 4. Exibe o relatório final com os dados acumulados pelo Singleton
        new RelatorioService().exibirRelatorio();
    }
}
