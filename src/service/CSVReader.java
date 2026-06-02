package service;

import model.Venda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pela leitura e parse do arquivo CSV de vendas.
 *
 * Formato esperado do CSV:
 *   id,produto,categoria,valor,quantidade
 *
 * Funcionalidades:
 *   - Lê o arquivo linha por linha com BufferedReader (eficiente para grandes volumes);
 *   - Ignora o cabeçalho (primeira linha);
 *   - Ignora linhas em branco ou malformadas, registrando aviso no console;
 *   - Retorna uma List<Venda> pronta para uso pelos demais integrantes.
 *
 * Autor: Integrante 1 — Modelo de dados + Leitura do CSV
 */
public class CSVReader {

    // -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------

    /** Separador de colunas do arquivo CSV. */
    private static final String SEPARADOR = ",";

    /** Índices das colunas no CSV (baseado no cabeçalho definido no enunciado). */
    private static final int COL_ID         = 0;
    private static final int COL_PRODUTO    = 1;
    private static final int COL_CATEGORIA  = 2;
    private static final int COL_VALOR      = 3;
    private static final int COL_QUANTIDADE = 4;

    /** Número total de colunas esperadas em cada linha de dados. */
    private static final int TOTAL_COLUNAS = 5;

    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------

    /** Caminho absoluto ou relativo do arquivo CSV a ser lido. */
    private final String caminhoArquivo;

    // -------------------------------------------------------------------------
    // Construtor
    // -------------------------------------------------------------------------

    /**
     * Cria um CSVReader apontado para o arquivo informado.
     *
     * @param caminhoArquivo Caminho do arquivo CSV (ex: "src/resources/vendas.csv").
     */
    public CSVReader(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // -------------------------------------------------------------------------
    // Método principal
    // -------------------------------------------------------------------------

    /**
     * Lê o arquivo CSV e retorna uma lista de objetos {@link Venda}.
     *
     * O método:
     * 1. Abre o arquivo com BufferedReader para leitura eficiente;
     * 2. Pula o cabeçalho (primeira linha);
     * 3. Para cada linha de dados, chama {@link #parseLinha(String, int)};
     * 4. Adiciona o objeto Venda à lista se o parse for bem-sucedido;
     * 5. Ignora silenciosamente linhas vazias ou malformadas (com aviso).
     *
     * @return Lista de {@link Venda} com todos os registros válidos do arquivo.
     * @throws IOException Se o arquivo não for encontrado ou houver erro de leitura.
     */
    public List<Venda> lerVendas() throws IOException {

        List<Venda> vendas = new ArrayList<>();

        System.out.println("[CSVReader] Iniciando leitura do arquivo: " + caminhoArquivo);

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;
            int numeroLinha = 0;

            while ((linha = br.readLine()) != null) {
                numeroLinha++;

                // Pula o cabeçalho (primeira linha do CSV)
                if (numeroLinha == 1) {
                    System.out.println("[CSVReader] Cabecalho ignorado: " + linha);
                    continue;
                }

                // Ignora linhas completamente em branco
                if (linha.trim().isEmpty()) {
                    continue;
                }

                // Tenta fazer o parse da linha em um objeto Venda
                Venda venda = parseLinha(linha, numeroLinha);

                // Adiciona somente se o parse foi bem-sucedido
                if (venda != null) {
                    vendas.add(venda);
                }
            }
        }

        System.out.println("[CSVReader] Leitura concluida. Total de registros validos: " + vendas.size());
        return vendas;
    }

    // -------------------------------------------------------------------------
    // Método auxiliar de parse
    // -------------------------------------------------------------------------

    /**
     * Converte uma linha do CSV em um objeto {@link Venda}.
     *
     * Realiza validações básicas:
     * - Número correto de colunas;
     * - Tipos numéricos válidos (int e double);
     * - Campos de texto não vazios.
     *
     * Em caso de erro, exibe aviso no console e retorna {@code null}.
     *
     * @param linha       Linha bruta do arquivo CSV.
     * @param numeroLinha Número da linha no arquivo (para mensagens de erro).
     * @return Objeto {@link Venda} preenchido, ou {@code null} se a linha for inválida.
     */
    private Venda parseLinha(String linha, int numeroLinha) {
        try {
            // Divide a linha pelos separadores
            String[] campos = linha.split(SEPARADOR);

            // Valida número de colunas
            if (campos.length != TOTAL_COLUNAS) {
                System.err.printf(
                    "[CSVReader] AVISO — Linha %d ignorada: esperava %d colunas, encontrou %d. Conteudo: '%s'%n",
                    numeroLinha, TOTAL_COLUNAS, campos.length, linha
                );
                return null;
            }

            // Faz o parse de cada campo com trim() para remover espaços extras
            int    id         = Integer.parseInt(campos[COL_ID].trim());
            String produto    = campos[COL_PRODUTO].trim();
            String categoria  = campos[COL_CATEGORIA].trim();
            double valor      = Double.parseDouble(campos[COL_VALOR].trim());
            int    quantidade = Integer.parseInt(campos[COL_QUANTIDADE].trim());

            // Valida campos de texto obrigatórios
            if (produto.isEmpty() || categoria.isEmpty()) {
                System.err.printf(
                    "[CSVReader] AVISO — Linha %d ignorada: campos 'produto' ou 'categoria' vazios.%n",
                    numeroLinha
                );
                return null;
            }

            // Valida valores numéricos positivos
            if (valor < 0 || quantidade < 0) {
                System.err.printf(
                    "[CSVReader] AVISO — Linha %d ignorada: 'valor' ou 'quantidade' não podem ser negativos.%n",
                    numeroLinha
                );
                return null;
            }

            return new Venda(id, produto, categoria, valor, quantidade);

        } catch (NumberFormatException e) {
            // Captura erros de conversão numérica (ex.: letras em campos numéricos)
            System.err.printf(
                "[CSVReader] AVISO — Linha %d ignorada: erro de formato numerico — %s%n",
                numeroLinha, e.getMessage()
            );
            return null;
        }
    }

    // -------------------------------------------------------------------------
    // Getter utilitário
    // -------------------------------------------------------------------------

    /**
     * Retorna o caminho do arquivo CSV configurado neste leitor.
     * @return Caminho do arquivo.
     */
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}
