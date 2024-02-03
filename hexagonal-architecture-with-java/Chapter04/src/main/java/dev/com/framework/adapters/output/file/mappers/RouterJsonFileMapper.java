package dev.com.framework.adapters.output.file.mappers;

import java.util.List;
import java.util.stream.Collectors;

import dev.com.domain.entity.Prima;
import dev.com.domain.entity.Switch;
import dev.com.domain.vo.*;
import dev.com.framework.adapters.output.file.json.*;

public class RouterJsonFileMapper {

    public static Prima toDomain(RouterJson routerJson){
        var routerId = PrimaId.withId(routerJson.getRouterId().toString());
        var routerType = RouterType.valueOf(routerJson.getRouterType().name());
        var switchId = SwitchId.withId(routerJson.getNetworkSwitch().getSwitchId().toString());
        var switchType = SwitchType.valueOf(routerJson.getNetworkSwitch().getSwitchType().toString());
        var ip = IP.fromAddress(routerJson.getNetworkSwitch().getIp().getAddress());
        var networks =  getNetworksFromJson(routerJson.getNetworkSwitch().getNetworks());

        var networkSwitch = new Switch(switchId, switchType, networks, ip);

        return new Prima(routerType, routerId, networkSwitch);
    }

    public static RouterJson toJson(Prima router){
        var routerId = router.getRouterId().getUUID();
        var routerTypeJson = RouterTypeJson.valueOf(router.getRouterType().toString());
        var switchIdJson = router.getNetworkSwitch().getSwitchId().getUUID();
        var switchTypeJson = SwitchTypeJson.valueOf(router.getNetworkSwitch().getSwitchType().toString());
        var ipJson = IPJson.fromAddress(router.getNetworkSwitch().getAddress().getIPAddress());
        var networkDataList = getNetworksFromDomain(router.retrieveNetworks());

        var switchJson = new SwitchJson(switchIdJson, ipJson, switchTypeJson, networkDataList);

        return new RouterJson(routerId, routerTypeJson, switchJson);
    }

    private static List<Cobertura> getNetworksFromJson(List<NetworkJson> networkJson){
        return networkJson
                .stream()
                .map(json ->  new Cobertura(
                        IP.fromAddress(json.getIp().getAddress()),
                        json.getNetworkName(),
                        Integer.valueOf(json.getCidr())))
                .collect(Collectors.toList());
    }

    private static List<NetworkJson>  getNetworksFromDomain(List<Cobertura> networks){
        return networks
                .stream()
                .map(network -> new NetworkJson(
                        IPJson.fromAddress(network.getAddress().getIPAddress()),
                        network.getName(),
                        String.valueOf(network.getCidr())))
                .collect(Collectors.toList());
    }

}
