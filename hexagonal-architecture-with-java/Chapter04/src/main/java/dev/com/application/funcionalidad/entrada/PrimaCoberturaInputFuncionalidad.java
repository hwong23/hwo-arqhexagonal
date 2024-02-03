package dev.com.application.funcionalidad.entrada;

import dev.com.application.funcionalidad.salida.PrimaFuncionalidadOutputFuncionalidad;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Router;
import dev.com.domain.service.NetworkOperation;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public class PrimaCoberturaInputFuncionalidad implements PrimaCoberturaUseCase {

    private final PrimaFuncionalidadOutputFuncionalidad routerNetworkOutputPort;

    public PrimaCoberturaInputFuncionalidad (
        PrimaFuncionalidadOutputFuncionalidad routerNetworkOutputPort)
    {
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter :
                router;
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}
