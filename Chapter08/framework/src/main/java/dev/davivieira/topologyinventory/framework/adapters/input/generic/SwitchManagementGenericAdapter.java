package dev.davivieira.topologyinventory.framework.adapters.input.generic;

import dev.davivieira.topologyinventory.application.ports.input.RouterManagementInputPort;
import dev.davivieira.topologyinventory.application.ports.input.SwitchManagementInputPort;
import dev.davivieira.topologyinventory.application.usecases.RouterManagementUseCase;
import dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.*;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

public class SwitchManagementGenericAdapter {

    private SwitchManagementUseCase switchManagementUseCase;
    private RouterManagementUseCase routerManagementUseCase;

    public SwitchManagementGenericAdapter(){
        setPorts();
    }

    private void setPorts(){
        this.routerManagementUseCase = new RouterManagementInputPort(
                RouterManagementH2Adapter.getInstance()
        );
        this.switchManagementUseCase = new SwitchManagementInputPort(
                SwitchManagementH2Adapter.getInstance()
        );
    }

    /**
     * GET /switch/retrieve/{id}
     * */
    public Plan retrieveSwitch(Id switchId) {
        return switchManagementUseCase.retrieveSwitch(switchId);
    }

    /**
     * POST /switch/create
     * */
    public EdgePrima createAndAddSwitchToEdgeRouter(
            Vendor vendor,
            Model model,
            IP ip,
            Location location,
            PlanType switchType,
            Id routerId
    ) {
        Plan newSwitch = switchManagementUseCase.createSwitch(vendor, model, ip, location, switchType);
        Prima edgeRouter = routerManagementUseCase.retrieveRouter(routerId);
        if(!edgeRouter.getRouterType().equals(PrimaType.EDGE))
            throw new UnsupportedOperationException("Please inform the id of an edge router to add a switch");
        Prima router = switchManagementUseCase.addSwitchToEdgeRouter(newSwitch, (EdgePrima) edgeRouter);
        return (EdgePrima) routerManagementUseCase.persistRouter(router);
    }

    /**
     * POST /switch/remove
     * */
    public EdgePrima removeSwitchFromEdgeRouter(Id switchId, Id edgeRouterId) {
        EdgePrima edgeRouter = (EdgePrima) routerManagementUseCase
                .retrieveRouter(edgeRouterId);
        Plan networkSwitch = edgeRouter.getSwitches().get(switchId);
        Prima router = switchManagementUseCase
                .removeSwitchFromEdgeRouter(networkSwitch, edgeRouter);
        return (EdgePrima) routerManagementUseCase.persistRouter(router);
    }
}
