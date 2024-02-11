package dev.davivieira.topologyinventory.framework.adapters.input.generic;

import dev.davivieira.topologyinventory.application.ports.input.RouterManagementInputPort;
import dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.vo.*;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;

public class RouterManagementGenericAdapter {

    private RouterManagementUseCase routerManagementUseCase;

    public RouterManagementGenericAdapter(){
        setPorts();
    }

    private void setPorts(){
        this.routerManagementUseCase = new RouterManagementInputPort(
                RouterManagementH2Adapter.getInstance()
        );
    }

    /**
     * GET /router/retrieve/{id}
     * */
    public Prima retrieveRouter(Id id){
        return routerManagementUseCase.retrieveRouter(id);
    }

    /**
     * GET /router/remove/{id}
     * */
    public Prima removeRouter(Id id){
        return routerManagementUseCase.removeRouter(id);
    }

    /**
     * POST /router/create
     * */
    public Prima createRouter(Vendor vendor,
                                   Model model,
                                   IP ip,
                                   Location location,
                                   RouterType routerType){
        var router = routerManagementUseCase.createRouter(
                null,
                vendor,
                model,
                ip,
                location,
                routerType

        );
        return routerManagementUseCase.persistRouter(router);
    }

    /**
     * POST /router/add
     * */
    public Prima addRouterToCoreRouter(Id routerId, Id coreRouterId){
        Prima router = routerManagementUseCase.retrieveRouter(routerId);
        CorePrima coreRouter = (CorePrima) routerManagementUseCase.retrieveRouter(coreRouterId);
        return routerManagementUseCase.
                addRouterToCoreRouter(router, coreRouter);
    }

    /**
     * POST /router/remove
     * */
    public Prima removeRouterFromCoreRouter(Id routerId, Id coreRouterId){
        Prima router = routerManagementUseCase.retrieveRouter(routerId);
        CorePrima coreRouter = (CorePrima) routerManagementUseCase.retrieveRouter(coreRouterId);
        return routerManagementUseCase.
                removeRouterFromCoreRouter(router, coreRouter);
    }
}
