package model;

/**
 * Classe modelo que representa um registro de venda.
 * Cada instância corresponde a uma linha do arquivo CSV de vendas.
 *
 * Campos do CSV esperado:
 *   id, produto, categoria, valor, quantidade
 *
 * Autor: Integrante 1 — Modelo de dados + Leitura do CSV
 */
public class Venda {

    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------

    /** Identificador único da venda (coluna "id"). */
    private int id;

    /** Nome do produto vendido (coluna "produto"). */
    private String produto;

    /** Categoria do produto (coluna "categoria"). */
    private String categoria;

    /** Valor unitário do produto em reais (coluna "valor"). */
    private double valor;

    /** Quantidade de unidades vendidas (coluna "quantidade"). */
    private int quantidade;

    // -------------------------------------------------------------------------
    // Construtores
    // -------------------------------------------------------------------------

    /**
     * Construtor padrão (sem argumentos).
     * Necessário para serialização/desserialização e reflexão.
     */
    public Venda() {}

    /**
     * Construtor completo.
     *
     * @param id         Identificador único da venda.
     * @param produto    Nome do produto.
     * @param categoria  Categoria do produto.
     * @param valor      Valor unitário.
     * @param quantidade Quantidade vendida.
     */
    public Venda(int id, String produto, String categoria, double valor, int quantidade) {
        this.id         = id;
        this.produto    = produto;
        this.categoria  = categoria;
        this.valor      = valor;
        this.quantidade = quantidade;
    }

    // -------------------------------------------------------------------------
    // Método utilitário
    // -------------------------------------------------------------------------

    /**
     * Calcula o valor total desta venda (valor unitário × quantidade).
     *
     * @return Total em reais desta venda.
     */
    public double calcularTotal() {
        return this.valor * this.quantidade;
    }

    // -------------------------------------------------------------------------
    // Getters e Setters
    // -------------------------------------------------------------------------

    /**
     * Retorna o identificador da venda.
     * @return id da venda.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da venda.
     * @param id Novo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * @return Nome do produto.
     */
    public String getProduto() {
        return produto;
    }

    /**
     * Define o nome do produto.
     * @param produto Nome do produto.
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * Retorna a categoria do produto.
     * @return Categoria.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     * @param categoria Categoria.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna o valor unitário do produto.
     * @return Valor em reais.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor unitário do produto.
     * @param valor Valor em reais.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna a quantidade vendida.
     * @return Quantidade de unidades.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade vendida.
     * @param quantidade Quantidade de unidades.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    /**
     * Representação textual do objeto Venda.
     * Útil para debug e geração de relatórios no terminal.
     *
     * @return String formatada com todos os campos da venda.
     */
    @Override
    public String toString() {
        return String.format(
            "Venda{id=%d, produto='%s', categoria='%s', valor=R$%.2f, quantidade=%d, total=R$%.2f}",
            id, produto, categoria, valor, quantidade, calcularTotal()
        );
    }
}
