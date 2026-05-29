package service;

import singleton.RelatorioSingleton;

public class RelatorioService {

    // obs: deve ser chamado somente dps do thread.join() na Main
    public void exibirRelatorio() {
        RelatorioSingleton r = RelatorioSingleton.getInstance();

        System.out.println("\n=== RELATORIO DE VENDAS ===");
        System.out.printf("Total vendido : R$ %,.2f%n", r.getTotalVendas());
        System.out.printf("Total de itens: %d unidades%n", r.getTotalItens());
        System.out.printf("Mais vendido  : %s%n", r.getMaisVendido());
        System.out.println("===========================\n");
    }
}