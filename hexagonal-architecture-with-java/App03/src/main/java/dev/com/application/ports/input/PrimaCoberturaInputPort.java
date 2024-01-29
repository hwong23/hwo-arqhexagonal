package dev.com.application.ports.input;

import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.NetworkOperation;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

public class PrimaCoberturaInputPort implements PrimaCoberturaUseCase {

    private final RouterNetworkOutputPort routerNetworkOutputPort;

    public PrimaCoberturaInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Prima addNetworkToRouter(PrimaId routerId, Cobertura network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Prima fetchRouter(PrimaId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Prima createNetwork(Prima router, Cobertura network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter :
                router;
    }

    private boolean persistNetwork(Prima router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
