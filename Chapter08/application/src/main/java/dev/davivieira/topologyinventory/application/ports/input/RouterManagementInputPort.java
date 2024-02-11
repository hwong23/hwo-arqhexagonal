package dev.davivieira.topologyinventory.application.ports.input;

import dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort;
import dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.entity.factory.RouterFactory;
import dev.davivieira.topologyinventory.domain.vo.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RouterManagementInputPort implements RouterManagementUseCase {

    RouterManagementOutputPort routerManagementOutputPort;

    public RouterManagementInputPort(RouterManagementOutputPort routerNetworkOutputPort){
        this.routerManagementOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Prima createRouter(Id id,
                               Vendor vendor,
                               Model model,
                               IP ip,
                               Location location,
                               PrimaType routerType) {
        return RouterFactory.getRouter(null,
                vendor,
                model,
                ip,
                location,
                routerType
        );
    }

    @Override
    public Prima removeRouter(Id id) {
        return routerManagementOutputPort.removeRouter(id);
    }

    @Override
    public Prima retrieveRouter(Id id) {
        return routerManagementOutputPort.retrieveRouter(id);
    }

    @Override
    public Prima persistRouter(Prima router) {
        return routerManagementOutputPort.persistRouter(router);
    }

    @Override
    public CorePrima addRouterToCoreRouter(Prima router, CorePrima coreRouter) {
        var addedRouter = coreRouter.addRouter(router);
        return (CorePrima) persistRouter(addedRouter);
    }

    @Override
    public Prima removeRouterFromCoreRouter(Prima router, CorePrima coreRouter) {
        var removedRouter = coreRouter.removeRouter(router);
        persistRouter(coreRouter);
        return removedRouter;
    }
}