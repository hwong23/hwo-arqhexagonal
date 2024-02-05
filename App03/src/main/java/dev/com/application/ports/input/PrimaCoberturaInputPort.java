package dev.com.application.ports.input;

import dev.com.application.ports.output.PrimaCoberturaOutputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.CoberturaOperation;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

public class PrimaCoberturaInputPort implements PrimaCoberturaUseCase {

    private final PrimaCoberturaOutputPort primaCoberturaOutputPort;

    public PrimaCoberturaInputPort(PrimaCoberturaOutputPort routerNetworkOutputPort){
        this.primaCoberturaOutputPort = routerNetworkOutputPort;
    }

    // Acciones del CU
    @Override
    public Prima addCoberturaToPrima (PrimaId primaId, Cobertura cobertura) {
        var router = fetchPrima(primaId);
        return createCobertura(router, cobertura);
    }

    private Prima fetchPrima (PrimaId primaId) {
        return primaCoberturaOutputPort.fetchRouterById(primaId);
    }

    private Prima createCobertura (Prima prima, Cobertura cobertura) {
        // servicio CoberturaOperation
        var newPrima = CoberturaOperation.createNewCobertura(prima, cobertura);
        return persistCobertura(prima) ? newPrima :
                prima;
    }

    private boolean persistCobertura (Prima router) {
        return primaCoberturaOutputPort.persistRouter(router);
    }
}
