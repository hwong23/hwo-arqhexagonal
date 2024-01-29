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
    public Prima addCoberturaToPrima (PrimaId routerId, Cobertura network) {
        var router = fetchPrima(routerId);
        return createCobertura(router, network);
    }

    private Prima fetchPrima (PrimaId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Prima createCobertura (Prima router, Cobertura network) {
        var newRouter = CoberturaOperation.createNewCobertura(router, network);
        return persistCobertura(router) ? newRouter :
                router;
    }

    private boolean persistCobertura (Prima router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
