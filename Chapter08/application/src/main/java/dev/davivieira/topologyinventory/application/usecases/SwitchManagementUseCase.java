package dev.davivieira.topologyinventory.application.usecases;

import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.*;

public interface SwitchManagementUseCase {

    Plan createSwitch(
            Vendor vendor,
            Model model,
            IP ip,
            Location location,
            PlanType switchType
    );

    Plan retrieveSwitch(Id id);

    EdgePrima addSwitchToEdgeRouter(Plan networkSwitch, EdgePrima edgeRouter);

    EdgePrima removeSwitchFromEdgeRouter(Plan networkSwitch, EdgePrima edgeRouter);
}

