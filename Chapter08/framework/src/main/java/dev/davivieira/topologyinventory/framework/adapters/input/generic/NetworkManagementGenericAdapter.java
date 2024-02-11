package dev.davivieira.topologyinventory.framework.adapters.input.generic;

import dev.davivieira.topologyinventory.application.ports.input.NetworkManagementInputPort;
import dev.davivieira.topologyinventory.application.ports.input.SwitchManagementInputPort;
import dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase;
import dev.davivieira.topologyinventory.application.usecases.SwitchManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Cobertura;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.RouterManagementH2Adapter;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.SwitchManagementH2Adapter;

public class NetworkManagementGenericAdapter {

    private SwitchManagementUseCase switchManagementUseCase;
    private NetworkManagementUseCase networkManagementUseCase;

    public NetworkManagementGenericAdapter(){
        setPorts();
    }

    private void setPorts(){
        this.switchManagementUseCase = new SwitchManagementInputPort(SwitchManagementH2Adapter.getInstance());
        this.networkManagementUseCase = new NetworkManagementInputPort(RouterManagementH2Adapter.getInstance());
    }

    /**
     * POST /network/add
     * */
    public Plan addNetworkToSwitch(Cobertura network, Id switchId) {
        Plan networkSwitch = switchManagementUseCase.retrieveSwitch(switchId);
        return networkManagementUseCase.addNetworkToSwitch(network, networkSwitch);
    }

    /**
     * POST /network/remove
     * */
    public Plan removeNetworkFromSwitch(String networkName, Id switchId) {
        Plan networkSwitch = switchManagementUseCase.retrieveSwitch(switchId);
        return networkManagementUseCase.removeNetworkFromSwitch(networkName, networkSwitch);
    }
}
