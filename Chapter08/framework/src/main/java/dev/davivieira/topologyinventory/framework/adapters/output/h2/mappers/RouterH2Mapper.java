package dev.davivieira.topologyinventory.framework.adapters.output.h2.mappers;


import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.entity.factory.RouterFactory;
import dev.davivieira.topologyinventory.domain.vo.*;
import dev.davivieira.topologyinventory.framework.adapters.output.h2.data.*;

import java.util.*;

public class RouterH2Mapper {

    public static Prima routerDataToDomain(RouterData routerData){
        var router = RouterFactory.getRouter(
                Id.withId(routerData.getRouterId().toString()),
                Vendor.valueOf(routerData.getRouterVendor().toString()),
                Model.valueOf(routerData.getRouterModel().toString()),
                IP.fromAddress(routerData.getIp().getAddress()),
                locationDataToLocation(routerData.getRouterLocation()),
                PrimaType.valueOf(routerData.getRouterType().name()));
        if(routerData.getRouterType().equals(RouterTypeData.CORE)){
            var coreRouter = (CorePrima) router;
            coreRouter.setRouters(getRoutersFromData(routerData.getRouters()));
            return coreRouter;
        } else {
            var edgeRouter = (EdgePrima) router;
            edgeRouter.setSwitches(getSwitchesFromData(routerData.getSwitches()));
            return edgeRouter;
        }
    }

    public static RouterData routerDomainToData(Prima router){
        var routerData = RouterData.builder().
                routerId(router.getId().getUuid()).
                routerVendor(VendorData.valueOf(router.getVendor().toString())).
                routerModel(ModelData.valueOf(router.getModel().toString())).
                ip(IPData.fromAddress(router.getIp().getIpAddress())).
                routerLocation(locationDomainToLocationData(router.getLocation())).
                routerType(RouterTypeData.valueOf(router.getRouterType().toString())).
                build();
        if(router.getRouterType().equals(PrimaType.CORE)) {
            var coreRouter = (CorePrima) router;
            routerData.setRouters(getRoutersFromDomain(coreRouter.getRouters()));
        } else {
            var edgeRouter = (EdgePrima) router;
            routerData.setSwitches(getSwitchesFromDomain(edgeRouter.getSwitches()));
        }
        return routerData;
    }

    public static Plan switchDataToDomain(SwitchData switchData) {
        return Plan.builder().
                id(Id.withId(switchData.getSwitchId().toString())).
                routerId(Id.withId(switchData.getRouterId().toString())).
                vendor(Vendor.valueOf(switchData.getSwitchVendor().toString())).
                model(Model.valueOf(switchData.getSwitchModel().toString())).
                ip(IP.fromAddress(switchData.getIp().getAddress())).
                location(locationDataToLocation(switchData.getSwitchLocation())).
                switchType(PlanType.valueOf(switchData.getSwitchType().toString())).
                switchNetworks(getNetworksFromData(switchData.getNetworks())).
                build();
    }

    public static SwitchData switchDomainToData(Plan aSwitch){
        return  SwitchData.builder().
                switchId(aSwitch.getId().getUuid()).
                routerId(aSwitch.getRouterId().getUuid()).
                switchVendor(VendorData.valueOf(aSwitch.getVendor().toString())).
                switchModel(ModelData.valueOf(aSwitch.getModel().toString())).
                ip(IPData.fromAddress(aSwitch.getIp().getIpAddress())).
                switchLocation(locationDomainToLocationData(aSwitch.getLocation())).
                switchType(SwitchTypeData.valueOf(aSwitch.getSwitchType().toString())).
                networks(getNetworksFromDomain(aSwitch.getSwitchNetworks(), aSwitch.getId().getUuid())).
                build();
    }

    public static Location locationDataToLocation(LocationData locationData){
        return new Location(
                locationData.getAddress(),
                locationData.getCity(),
                locationData.getState(),
                locationData.getZipcode(),
                locationData.getCountry(),
                locationData.getLatitude(),
                locationData.getLongitude());
    }

    public static LocationData locationDomainToLocationData(Location location){
        return LocationData.builder()
                .address(location.address())
                .city(location.city())
                .state(location.state())
                .zipcode(location.zipCode())
                .country(location.country())
                .latitude(location.latitude())
                .longitude(location.longitude())
                .build();
    }

    private static Map<Id, Prima> getRoutersFromData(List<RouterData> routerDataList){
        Map<Id,Prima> routerMap = new HashMap<>();
        for (RouterData routerData : routerDataList) {
            routerMap.put(
                    Id.withId(routerData.getRouterId().toString()),
                    routerDataToDomain(routerData));
        }
        return routerMap;
    }

    private static List<RouterData>  getRoutersFromDomain(Map<Id, Prima> routers){
        List<RouterData> routerDataList = new ArrayList<>();
         routers.values().stream().forEach(router -> {
             var routerData = routerDomainToData(router);
             routerDataList.add(routerData);
         });
        return routerDataList;
    }

    private static Map<Id, Plan> getSwitchesFromData(List<SwitchData> switchDataList){
        Map<Id,Plan> switchMap = new HashMap<>();
        for (SwitchData switchData : switchDataList) {
            switchMap.put(
                    Id.withId(switchData.getSwitchId().toString()),
                    switchDataToDomain(switchData));
        }
        return switchMap;
    }

    private static List<SwitchData>  getSwitchesFromDomain(Map<Id, Plan> switches){
        List<SwitchData> switchDataList = new ArrayList<>();
        if(switches!=null) {
            switches.values().stream().forEach(aSwitch -> {
                switchDataList.add(switchDomainToData(aSwitch));
            });
        }
        return switchDataList;
    }

    private static List<Cobertura> getNetworksFromData(List<NetworkData> networkData){
        List<Cobertura> networks = new ArrayList<>();
        networkData.forEach(data ->{
            var network = new Cobertura(
                    IP.fromAddress(data.getIp().getAddress()),
                    data.getName(),
                    data.getCidr());
            networks.add(network);
        });
        return networks;
    }

    private static List<NetworkData>  getNetworksFromDomain(List<Cobertura> networks, UUID routerId){
        List<NetworkData> networkDataList = new ArrayList<>();
        if(networks!=null) {
            networks.forEach(network -> {
                var networkData = new NetworkData(
                        routerId,
                        IPData.fromAddress(network.getNetworkAddress().getIpAddress()),
                        network.getNetworkName(),
                        network.getNetworkCidr()
                );
                networkDataList.add(networkData);
            });
        }
        return networkDataList;
    }

}
