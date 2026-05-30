package service;

import model.Venda;
import singleton.RelatorioSingleton;
import java.util.List;

// Implementação concreta do processador de vendas
public class VendaProcessorImpl implements VendaProcessor {

    @Override
    public void processar(List<Venda> vendas) {

        double totalBloco = 0.0;
        int itensBloco = 0;
        String produtoMaisVendido = "";
        int maiorQtd = 0;

        for (Venda v : vendas) {
            totalBloco += v.calcularTotal();
            itensBloco += v.getQuantidade();

            if (v.getQuantidade() > maiorQtd) {
                maiorQtd = v.getQuantidade();
                produtoMaisVendido = v.getProduto();
            }
        }

        RelatorioSingleton.getInstance()
            .acumularResultados(totalBloco, itensBloco, produtoMaisVendido, maiorQtd);
    }
}