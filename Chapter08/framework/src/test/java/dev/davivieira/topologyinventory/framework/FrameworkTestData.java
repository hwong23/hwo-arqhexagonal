package dev.davivieira.topologyinventory.framework;

import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameworkTestData {
    protected List<Prima> routers = new ArrayList<>();

    protected List<Plan> switches = new ArrayList<>();

    protected List<Cobertura> networks = new ArrayList<>();

    protected Map<Id, Prima> routersOfCoreRouter = new HashMap<>();

    protected Map<Id, Plan> switchesOfEdgeRouter = new HashMap<>();

    protected Cobertura network;

    protected Plan networkSwitch;

    protected CorePrima coreRouter;

    protected CorePrima newCoreRouter;

    protected EdgePrima edgeRouter;

    protected EdgePrima newEdgeRouter;

    protected Location locationA;

    protected Location locationB;

    public void loadData(){
        this.locationA = new Location(
                "Amos Ln",
                "Tully",
                "NY",
                13159,
                "United States",
                42.797310F,
                -76.130750F);
        this.locationB = new Location(
                "Av Republica Argentina 3109",
                "Curitiba",
                "PR",
                80610260,
                "Brazil",
                10F,
                -10F);
        this.network  = Cobertura.builder().
                networkAddress(IP.fromAddress("20.0.0.0")).
                networkName("TestNetwork").
                networkCidr(8).
                build();
        this.networks.add(network);
        this.networkSwitch = Plan.builder().
                id(Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
                vendor(Vendor.CISCO).
                model(Model.XYZ0004).
                ip(IP.fromAddress("20.0.0.100")).
                location(locationA).
                switchType(PlanType.LAYER3).
                switchNetworks(networks).
                build();
        this.switchesOfEdgeRouter.put(networkSwitch.getId(), networkSwitch);
        this.edgeRouter = EdgePrima.builder().
                id(Id.withoutId()).
                vendor(Vendor.CISCO).
                model(Model.XYZ0002).
                ip(IP.fromAddress("20.0.0.1")).
                location(locationA).
                routerType(PrimaType.EDGE).
                switches(switchesOfEdgeRouter).
                build();
        this.routersOfCoreRouter.put(edgeRouter.getId(), edgeRouter);
        this.coreRouter = CorePrima.builder().
                id(Id.withoutId()).
                vendor(Vendor.HP).
                model(Model.XYZ0001).
                ip(IP.fromAddress("10.0.0.1")).
                location(locationA).
                routerType(PrimaType.CORE).
                routers(routersOfCoreRouter).
                build();
        this.newCoreRouter = CorePrima.builder().
                id(Id.withId("81579b05-4b4e-4b9b-91f4-75a5a8561296")).
                vendor(Vendor.HP).
                model(Model.XYZ0001).
                ip(IP.fromAddress("10.1.0.1")).
                location(locationA).
                routerType(PrimaType.CORE).
                build();
        this.newEdgeRouter = EdgePrima.builder().
                id(Id.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")).
                vendor(Vendor.CISCO).
                model(Model.XYZ0002).
                ip(IP.fromAddress("20.1.0.1")).
                location(locationA).
                routerType(PrimaType.EDGE).
                build();
    }
}
