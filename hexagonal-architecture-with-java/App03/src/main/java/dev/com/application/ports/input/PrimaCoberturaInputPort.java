package dev.com.application.ports.input;

import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.CoberturaOperation;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

public class PrimaCoberturaInputPort implements PrimaCoberturaUseCase {

    private final RouterNetworkOutputPort routerNetworkOutputPort;

    public PrimaCoberturaInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    // Acciones del CU
    @Override
    public Prima addCoberturaToPrima (PrimaId primaId, Cobertura cobertura) {
        var router = fetchPrima(primaId);
        return createCobertura(router, cobertura);
    }

    private Prima fetchPrima (PrimaId primaId) {
        return routerNetworkOutputPort.fetchRouterById(primaId);
    }

    private Prima createCobertura (Prima prima, Cobertura cobertura) {
        var newPrima = CoberturaOperation.createNewCobertura(prima, cobertura);
        return persistCobertura(prima) ? newPrima :
                prima;
    }

    private boolean persistCobertura (Prima router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
