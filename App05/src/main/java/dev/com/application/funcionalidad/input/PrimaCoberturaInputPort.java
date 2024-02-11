package dev.com.application.funcionalidad.input;

import dev.com.application.funcionalidad.output.NotifyEventOutputPort;
import dev.com.application.funcionalidad.output.PrimaCoberturaOutputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.CoberturaOperation;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

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
    public Prima addNetworkToRouter(PrimaId routerId, Cobertura network) {
        var router = fetchRouter(routerId);
        notifyEventOutputPort.sendEvent("Adding "+network.getName()+" cobertura to prima "+router.getRouterId().getUUID());
        return createNetwork(router, network);
    }

    @Override
    public Prima getRouter(PrimaId routerId) {
        notifyEventOutputPort.sendEvent("Retrieving prima ID "+routerId.getUUID());
        return fetchRouter(routerId);
    }

    private Prima fetchRouter(PrimaId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Prima createNetwork(Prima router, Cobertura network) {
        try {
            var routerWithNewNetwork = CoberturaOperation.createNewNetwork(router, network);
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
