package factory;

import service.VendaProcessor;
import model.Venda;
import java.util.List;

// Define o método criarProcessador() que as subclasses devem implementar
public abstract class ProcessorFactory {

    public abstract VendaProcessor criarProcessador();

    public void processar(List<Venda> vendas) {
        VendaProcessor processador = criarProcessador();
        processador.processar(vendas);
    }
}