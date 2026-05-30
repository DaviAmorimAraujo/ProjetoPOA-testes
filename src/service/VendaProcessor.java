package service;

import model.Venda;
import java.util.List;

// Interface que define o contrato para os processadores de vendas
public interface VendaProcessor {
    void processar(List<Venda> vendas);
}