package dev.davivieira.topologyinventory.application.usecases;

import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Cobertura;

public interface NetworkManagementUseCase {

    Cobertura createNetwork(
            IP networkAddress,
            String networkName,
            int networkCidr);

    Plan addNetworkToSwitch(Cobertura network, Plan networkSwitch);

    Plan removeNetworkFromSwitch(String name, Plan networkSwitch);
}
