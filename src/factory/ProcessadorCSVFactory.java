package factory;

import service.VendaProcessor;
import service.VendaProcessorImpl;

// Criador concreto que decide qual processador instanciar
public class ProcessadorCSVFactory extends ProcessorFactory {

    @Override
    public VendaProcessor criarProcessador() {
        return new VendaProcessorImpl();
    }
}