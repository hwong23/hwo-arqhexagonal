package dev.davivieira.topologyinventory.application.ports.input;

import dev.davivieira.topologyinventory.application.ports.output.RouterManagementOutputPort;
import dev.davivieira.topologyinventory.application.usecases.NetworkManagementUseCase;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.service.CoberturaService;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Network;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@NoArgsConstructor
public class NetworkManagementInputPort implements NetworkManagementUseCase {

    RouterManagementOutputPort routerManagementOutputPort;

    public NetworkManagementInputPort(RouterManagementOutputPort routerNetworkOutputPort){
        this.routerManagementOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Network createNetwork(
            IP networkAddress, String networkName, int networkCidr) {
        return Network
                .builder()
                .networkAddress(networkAddress)
                .networkName(networkName)
                .networkCidr(networkCidr)
                .build();
    }

    @Override
    public Plan addNetworkToSwitch(Network network, Plan networkSwitch) {
        Id routerId = networkSwitch.getRouterId();
        Id switchId = networkSwitch.getId();
        EdgePrima edgeRouter = (EdgePrima) routerManagementOutputPort
                .retrieveRouter(routerId);
        Plan switchToAddNetwork = edgeRouter
                .getSwitches()
                .get(switchId);
        switchToAddNetwork.addNetworkToSwitch(network);
        routerManagementOutputPort.persistRouter(edgeRouter);
        return switchToAddNetwork;
    }

    @Override
    public Plan removeNetworkFromSwitch(String networkName, Plan networkSwitch) {
        Id routerId = networkSwitch.getRouterId();
        Id switchId = networkSwitch.getId();
        EdgePrima edgeRouter = (EdgePrima) routerManagementOutputPort
                .retrieveRouter(routerId);
        Plan switchToRemoveNetwork = edgeRouter
                .getSwitches()
                .get(switchId);
        Predicate<Network> networkPredicate = Network.getNetworkNamePredicate(networkName);
        var network = CoberturaService.
                findNetwork(switchToRemoveNetwork.getSwitchNetworks(), networkPredicate);
        switchToRemoveNetwork.removeNetworkFromSwitch(network);
        routerManagementOutputPort.persistRouter(edgeRouter);
        return switchToRemoveNetwork.removeNetworkFromSwitch(network)
                ? switchToRemoveNetwork
                : null;
    }
}
