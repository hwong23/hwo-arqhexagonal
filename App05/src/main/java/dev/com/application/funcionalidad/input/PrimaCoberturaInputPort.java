package dev.com.application.funcionalidad.input;

import dev.com.application.funcionalidad.output.NotifyEventOutputPort;
import dev.com.application.funcionalidad.output.PrimaCoberturaOutputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.NetworkOperation;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public class PrimaCoberturaInputPort implements PrimaCoberturaUseCase {

    private PrimaCoberturaOutputPort routerNetworkOutputPort;

    private NotifyEventOutputPort notifyEventOutputPort;

    public PrimaCoberturaInputPort(PrimaCoberturaOutputPort routerNetworkOutputPort,
                                  NotifyEventOutputPort notifyEventOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
        this.notifyEventOutputPort = notifyEventOutputPort;
    }

    public PrimaCoberturaInputPort(PrimaCoberturaOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Prima addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        notifyEventOutputPort.sendEvent("Adding "+network.getName()+" network to router "+router.getRouterId().getUUID());
        return createNetwork(router, network);
    }

    @Override
    public Prima getRouter(RouterId routerId) {
        notifyEventOutputPort.sendEvent("Retrieving router ID "+routerId.getUUID());
        return fetchRouter(routerId);
    }

    private Prima fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Prima createNetwork(Prima router, Network network) {
        try {
            var routerWithNewNetwork = NetworkOperation.createNewNetwork(router, network);
            return persistNetwork(routerWithNewNetwork) ?
                    routerWithNewNetwork: router;
        } catch (Exception e){
            System.out.println(e.getMessage());
             throw e;
        }
    }

    private boolean persistNetwork(Prima router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
