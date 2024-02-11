package dev.davivieira.topologyinventory.domain;

import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.exception.GenericSpecificationException;
import dev.davivieira.topologyinventory.domain.service.PrimaService;
import dev.davivieira.topologyinventory.domain.service.CoberturaService;
import dev.davivieira.topologyinventory.domain.service.PlanService;
import dev.davivieira.topologyinventory.domain.vo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DomainTest {

    @Test
    public void addNetworkToSwitch(){
        var location = createLocation("US");
        var newNetwork = createTestNetwork("30.0.0.1", 8);
        var networkSwitch = createSwitch("30.0.0.0", 8, location);
        assertTrue(networkSwitch.addNetworkToSwitch(newNetwork));
    }

    @Test
    public void addNetworkToSwitch_failBecauseSameNetworkAddress(){
        var location = createLocation("US");
        var newNetwork = createTestNetwork("30.0.0.0", 8);
        var networkSwitch = createSwitch("30.0.0.0", 8, location);
        assertThrows(GenericSpecificationException.class, () -> networkSwitch.addNetworkToSwitch(newNetwork));
    }

    @Test
    public void addSwitchToEdgeRouter(){
        var location = createLocation("US");
        var networkSwitch = createSwitch("30.0.0.0", 8, location);
        var edgeRouter = createEdgeRouter(location,"30.0.0.1");

        edgeRouter.addSwitch(networkSwitch);

        assertEquals(1,edgeRouter.getSwitches().size());
    }

    @Test
    public void addSwitchToEdgeRouter_failBecauseEquipmentOfDifferentCountries(){
        var locationUS = createLocation("US");
        var locationJP = createLocation("JP");
        var networkSwitch = createSwitch("30.0.0.0", 8, locationUS);
        var edgeRouter = createEdgeRouter(locationJP,"30.0.0.1");

        assertThrows(GenericSpecificationException.class, () -> edgeRouter.addSwitch(networkSwitch));
    }

    @Test
    public void addEdgeToCoreRouter(){
        var location = createLocation("US");
        var edgeRouter = createEdgeRouter(location,"30.0.0.1");
        var coreRouter = createCoreRouter(location, "40.0.0.1");

        coreRouter.addRouter(edgeRouter);

        assertEquals(1,coreRouter.getRouters().size());
    }

    @Test
    public void addEdgeToCoreRouter_failBecauseRoutersOfDifferentCountries(){
        var locationUS = createLocation("US");
        var locationJP = createLocation("JP");
        var edgeRouter = createEdgeRouter(locationUS,"30.0.0.1");
        var coreRouter = createCoreRouter(locationJP, "40.0.0.1");

        assertThrows(GenericSpecificationException.class, () -> coreRouter.addRouter(edgeRouter));
    }

    @Test
    public void addCoreToCoreRouter(){
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var newCoreRouter = createCoreRouter(location, "40.0.0.1");

        coreRouter.addRouter(newCoreRouter);

        assertEquals(1,coreRouter.getRouters().size());
    }

    @Test
    public void addCoreToCoreRouter_failBecauseRoutersOfSameIp(){
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var newCoreRouter = createCoreRouter(location, "30.0.0.1");

        assertThrows(GenericSpecificationException.class, () -> coreRouter.addRouter(newCoreRouter));
    }

    @Test
    public void removeRouter(){
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var edgeRouter = createEdgeRouter(location, "40.0.0.1");
        var expectedId = edgeRouter.getId();

        coreRouter.addRouter(edgeRouter);
        var actualId = coreRouter.removeRouter(edgeRouter).getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void removeSwitch(){
        var location = createLocation("US");
        var network = createTestNetwork("30.0.0.0", 8);
        var networkSwitch = createSwitch("30.0.0.0", 8, location);
        var edgeRouter = createEdgeRouter(location, "40.0.0.1");

        edgeRouter.addSwitch(networkSwitch);
        networkSwitch.removeNetworkFromSwitch(network);
        var expectedId = Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490");
        var actualId= edgeRouter.removeSwitch(networkSwitch).getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void removeNetwork(){
        var location = createLocation("US");
        var network = createTestNetwork("30.0.0.0", 8);
        var networkSwitch = createSwitch("30.0.0.0", 8, location);

        assertEquals(1, networkSwitch.getSwitchNetworks().size());
        assertTrue(networkSwitch.removeNetworkFromSwitch(network));
        assertEquals(0, networkSwitch.getSwitchNetworks().size());
    }

    @Test
    public void filterRouterByType(){
        List<Prima> routers = new ArrayList<>();
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var edgeRouter = createEdgeRouter(location, "40.0.0.1");

        routers.add(coreRouter);
        routers.add(edgeRouter);

        var coreRouters = PrimaService.filterAndRetrieveRouter(routers,
                Prima.getRouterTypePredicate(PrimaType.CORE));
        var actualCoreType = coreRouters.get(0).getRouterType();
        assertEquals(PrimaType.CORE, actualCoreType);

        var edgeRouters = PrimaService.filterAndRetrieveRouter(routers,
                Prima.getRouterTypePredicate(PrimaType.EDGE));
        var actualEdgeType = edgeRouters.get(0).getRouterType();
        assertEquals(PrimaType.EDGE, actualEdgeType);
    }

    @Test
    public void filterRouterByVendor(){
        List<Prima> routers = new ArrayList<>();
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var edgeRouter = createEdgeRouter(location, "40.0.0.1");

        routers.add(coreRouter);
        routers.add(edgeRouter);

        var actualVendor = PrimaService.filterAndRetrieveRouter(routers,
                Prima.getVendorPredicate(Vendor.HP)).get(0).getVendor();
        assertEquals(Vendor.HP, actualVendor);

        actualVendor = PrimaService.filterAndRetrieveRouter(routers,
                Prima.getVendorPredicate(Vendor.CISCO)).get(0).getVendor();
        assertEquals(Vendor.CISCO, actualVendor);
    }

    @Test
    public void filterRouterByLocation(){
        List<Prima> routers = new ArrayList<>();
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");

        routers.add(coreRouter);

        var actualCountry = PrimaService.filterAndRetrieveRouter(routers,
                Prima.getCountryPredicate(location)).get(0).getLocation().country();
        assertEquals(location.country(), actualCountry);
    }

    @Test
    public void filterRouterByModel(){
        List<Prima> routers = new ArrayList<>();
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var newCoreRouter = createCoreRouter(location, "40.0.0.1");

        coreRouter.addRouter(newCoreRouter);
        routers.add(coreRouter);

        var actualModel= PrimaService.filterAndRetrieveRouter(routers,
                Prima.getModelPredicate(Model.XYZ0001)).get(0).getModel();
        assertEquals(Model.XYZ0001, actualModel);
    }

    @Test
    public void filterSwitchByType(){
        List<Plan> switches = new ArrayList<>();
        var location = createLocation("US");
        var networkSwitch = createSwitch("30.0.0.0", 8, location);

        switches.add(networkSwitch);

        var actualSwitchType = PlanService.filterAndRetrieveSwitch(switches,
                Plan.getSwitchTypePredicate(PlanType.LAYER3)).get(0).getSwitchType();
        assertEquals(PlanType.LAYER3, actualSwitchType);
    }

    @Test
    public void filterNetworkByProtocol(){
        var testNetwork = createTestNetwork("30.0.0.0", 8);
        var networks = createNetworks(testNetwork);

        var expectedProtocol = Protocol.IPV4;
        var actualProtocol = CoberturaService.filterAndRetrieveNetworks(networks,
                Plan.getNetworkProtocolPredicate(Protocol.IPV4)).get(0).getNetworkAddress().getProtocol();
        assertEquals(expectedProtocol, actualProtocol);
    }

    @Test
    public void findRouterById(){
        List<Prima> routers = new ArrayList<>();
        Map<Id, Prima> routersOfCoreRouter = new HashMap<>();
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var newCoreRouter = createCoreRouter(location, "40.0.0.1");


        coreRouter.addRouter(newCoreRouter);
        routersOfCoreRouter.put(newCoreRouter.getId(), newCoreRouter);

        var expectedId = newCoreRouter.getId();
        var actualId = PrimaService.findById(routersOfCoreRouter, expectedId).getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void findSwitchById(){
        List<Plan> switches = new ArrayList<>();
        Map<Id, Plan> switchesOfEdgeRouter = new HashMap<>();
        var location = createLocation("US");
        var networkSwitch = createSwitch("30.0.0.0", 8, location);

        switchesOfEdgeRouter.put(networkSwitch.getId(), networkSwitch);

        var expectedId = Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490");
        var actualId = PlanService.findById(switchesOfEdgeRouter, expectedId).getId();
        assertEquals(expectedId, actualId);
    }

    private Cobertura createTestNetwork(String address, int CIDR){
        return Cobertura.builder().
                networkAddress(IP.fromAddress(address)).
                networkName("NewNetwork").
                networkCidr(CIDR).
                build();
    }

    private Location createLocation(String country){
        return new Location(
                "Test street",
                "Test City",
                "Test State",
                00000,
                country,
                10F,
                -10F
                );
    }

    private List<Cobertura> createNetworks(Cobertura network){
        List<Cobertura> networks = new ArrayList<>();
        networks.add(network);
        return networks;
    }

    private Plan createSwitch(String address, int cidr, Location location){
        var newNetwork = createTestNetwork(address, cidr);
        var networks = createNetworks(newNetwork);
        var networkSwitch = createNetworkSwitch(location, networks);
        return networkSwitch;
    }

    private Plan createNetworkSwitch(Location location, List<Cobertura> networks){
        return Plan.builder().
                id(Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
                vendor(Vendor.CISCO).
                model(Model.XYZ0004).
                ip(IP.fromAddress("20.0.0.100")).
                location(location).
                switchType(PlanType.LAYER3).
                switchNetworks(networks).
                build();
    }

    private EdgePrima createEdgeRouter(Location location, String address){
        Map<Id, Plan> switchesOfEdgeRouter = new HashMap<>();
        return EdgePrima.builder().
                id(Id.withoutId()).
                vendor(Vendor.CISCO).
                model(Model.XYZ0002).
                ip(IP.fromAddress(address)).
                location(location).
                routerType(PrimaType.EDGE).
                switches(switchesOfEdgeRouter).
                build();
    }

    private CorePrima createCoreRouter(Location location, String address){
        Map<Id, Prima> routersOfCoreRouter = new HashMap<>();
        return CorePrima.builder().
                id(Id.withoutId()).
                vendor(Vendor.HP).
                model(Model.XYZ0001).
                ip(IP.fromAddress(address)).
                location(location).
                routerType(PrimaType.CORE).
                routers(routersOfCoreRouter).
                build();
    }
}
