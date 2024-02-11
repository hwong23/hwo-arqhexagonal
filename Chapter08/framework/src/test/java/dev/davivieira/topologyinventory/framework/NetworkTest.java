package dev.davivieira.topologyinventory.framework;

import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.service.CoberturaService;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Cobertura;
import dev.davivieira.topologyinventory.framework.adapters.input.generic.NetworkManagementGenericAdapter;
import dev.davivieira.topologyinventory.framework.adapters.input.generic.SwitchManagementGenericAdapter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkTest extends FrameworkTestData {

    NetworkManagementGenericAdapter networkManagementGenericAdapter;
    SwitchManagementGenericAdapter switchManagementGenericAdapter;

    public NetworkTest(){
        this.networkManagementGenericAdapter = new NetworkManagementGenericAdapter();
        this.switchManagementGenericAdapter = new SwitchManagementGenericAdapter();
        loadData();
    }
    @Test
    @Order(1)
    public void addNetworkToSwitch(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb46");
        Plan networkSwitch = networkManagementGenericAdapter.addNetworkToSwitch(network, switchId);
        Predicate<Cobertura> predicate = Cobertura.getNetworkNamePredicate("TestNetwork");
        Cobertura actualNetwork = CoberturaService.findNetwork(networkSwitch.getSwitchNetworks(), predicate);
        assertEquals(network, actualNetwork);
    }
    @Test
    @Order(2)
    public void removeNetworkFromSwitch(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb46");
        var networkName = "HR";
        Predicate<Cobertura> predicate = Cobertura.getNetworkNamePredicate(networkName);
        Plan networkSwitch = switchManagementGenericAdapter.retrieveSwitch(switchId);
        Cobertura existentNetwork = CoberturaService.findNetwork(networkSwitch.getSwitchNetworks(), predicate);
        assertNotNull(existentNetwork);
        networkSwitch = networkManagementGenericAdapter.removeNetworkFromSwitch(networkName, switchId);
        assertNull(networkSwitch);
    }
}
