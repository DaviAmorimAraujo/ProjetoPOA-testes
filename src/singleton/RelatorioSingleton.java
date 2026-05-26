package singleton;

public class RelatorioSingleton {

    private static volatile RelatorioSingleton instancia;

    private double totalVendas;
    private int totalItens;
    private String maisVendido;
    private int maiorQuantidade;

    private RelatorioSingleton() {
        this.totalVendas = 0.0;
        this.totalItens = 0;
        this.maisVendido = "";
        this.maiorQuantidade = 0;
    }

    // lazy initialization: cria a instância so quando for necessario
    public static synchronized RelatorioSingleton getInstance() {
        if (instancia == null) {
            instancia = new RelatorioSingleton();
        }
        return instancia;
    }

    // synchronized: garante que so uma thread acumule por vez
    public synchronized void acumularResultados(double totalBloco,int itensBloco,String produtoBloco,int qtdProdutoBloco) {
        this.totalVendas += totalBloco;
        this.totalItens += itensBloco;

        if (qtdProdutoBloco > this.maiorQuantidade) {
            this.maiorQuantidade = qtdProdutoBloco;
            this.maisVendido = produtoBloco;
        }
    }

    public synchronized double getTotalVendas() {
      return totalVendas;
    }
    public synchronized int getTotalItens() {
      return totalItens;
    }
    public synchronized String getMaisVendido() {
      return maisVendido;
    }
}
