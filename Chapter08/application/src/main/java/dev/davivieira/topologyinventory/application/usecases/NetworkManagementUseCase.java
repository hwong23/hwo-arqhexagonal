package dev.davivieira.topologyinventory.application.usecases;

import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Network;

public interface NetworkManagementUseCase {

    Network createNetwork(
            IP networkAddress,
            String networkName,
            int networkCidr);

    Plan addNetworkToSwitch(Network network, Plan networkSwitch);

    Plan removeNetworkFromSwitch(String name, Plan networkSwitch);
}
