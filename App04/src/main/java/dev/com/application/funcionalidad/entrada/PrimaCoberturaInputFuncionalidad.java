package dev.com.application.funcionalidad.entrada;

import dev.com.application.funcionalidad.salida.PrimaFuncionalidadOutputFuncionalidad;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.CoberturaOperation;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public class PrimaCoberturaInputFuncionalidad implements PrimaCoberturaUseCase {

    private final PrimaFuncionalidadOutputFuncionalidad routerNetworkOutputPort;

    public PrimaCoberturaInputFuncionalidad(PrimaFuncionalidadOutputFuncionalidad routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Prima addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Prima fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Prima createNetwork(Prima router, Network network) {
        var newRouter = CoberturaOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter :
                router;
    }

    private boolean persistNetwork(Prima router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
