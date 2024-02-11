package dev.davivieira.topologyinventory.application.ports.input;

import dev.davivieira.topologyinventory.application.ports.output.SwitchManagementOutputPort;
import dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Location;
import dev.davivieira.topologyinventory.domain.vo.Model;
import dev.davivieira.topologyinventory.domain.vo.PlanType;
import dev.davivieira.topologyinventory.domain.vo.Vendor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SwitchManagementInputPort implements SwitchManagementUseCase {

    private SwitchManagementOutputPort switchManagementOutputPort;

    public SwitchManagementInputPort(SwitchManagementOutputPort switchManagementOutputPort){
        this.switchManagementOutputPort = switchManagementOutputPort;
    }

    @Override
    public Plan createSwitch(
            Vendor vendor,
            Model model,
            IP ip,
            Location location,
            PlanType switchType) {
        return Plan
                .builder()
                .id(Id.withoutId())
                .vendor(vendor)
                .model(model)
                .ip(ip)
                .location(location)
                .switchType(switchType)
                .build();
    }

    public Plan retrieveSwitch(Id id){
        return switchManagementOutputPort.retrieveSwitch(id);
    }

    @Override
    public EdgePrima addSwitchToEdgeRouter(Plan networkSwitch, EdgePrima edgeRouter) {
        networkSwitch.setRouterId(edgeRouter.getId());
        edgeRouter.addSwitch(networkSwitch);
        return edgeRouter;
    }

    @Override
    public EdgePrima removeSwitchFromEdgeRouter(Plan networkSwitch, EdgePrima edgeRouter) {
        edgeRouter.removeSwitch(networkSwitch);
        return edgeRouter;
    }
}
