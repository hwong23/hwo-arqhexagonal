package dev.com.application.ports.input;

import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.application.usecases.RouterNetworkUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.NetworkOperation;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.PrimaId;

public class RouterNetworkInputPort implements RouterNetworkUseCase {

    private final RouterNetworkOutputPort routerNetworkOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Prima addNetworkToRouter(PrimaId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Prima fetchRouter(PrimaId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Prima createNetwork(Prima router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter :
                router;
    }

    private boolean persistNetwork(Prima router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
