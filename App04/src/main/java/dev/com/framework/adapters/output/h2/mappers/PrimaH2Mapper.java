package dev.com.framework.adapters.output.h2.mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.com.domain.entity.Prima;
import dev.com.domain.entity.Plan;
import dev.com.domain.vo.*;
import dev.com.framework.adapters.output.h2.data.*;

public class PrimaH2Mapper {

        public static Prima toDomain(RouterData routerData){
            var routerType = PrimaType.valueOf(routerData.getRouterType().name());
            var routerId = PrimaId.withId(routerData.getRouterId().toString());
            var switchId = PlanId.withId(routerData.getNetworkSwitch().getSwitchId().toString());
            var switchType = PlanType.valueOf(routerData.getNetworkSwitch().getSwitchType().toString());
            var ip = IP.fromAddress(routerData.getNetworkSwitch().getIp().getAddress());
            var networks =  getNetworksFromData(routerData.getNetworkSwitch().getNetworks());

            var networkSwitch = new Plan(switchId, switchType,networks, ip);
            return new Prima(routerType, routerId, networkSwitch);
        }


        public static RouterData toH2(Prima router){
            var routerTypeData = RouterTypeData.valueOf(router.getRouterType().toString());
            var routerId = router.getRouterId().getUUID();
            var switchId = router.getNetworkSwitch().getSwitchId().getUUID();
            var switchTypeData = SwitchTypeData.valueOf(router.getNetworkSwitch().getSwitchType().toString());
            var ipData = IPData.fromAddress(router.getNetworkSwitch().getAddress().getIPAddress());
            var networkDataList = getNetworksFromDomain(router.retrieveNetworks(), routerId);

            var switchData = new SwitchData(
                    routerId,
                    switchId,
                    switchTypeData,
                    networkDataList,
                    ipData);
            return new RouterData(routerId, routerTypeData, switchData);
        }

        private static List<Cobertura> getNetworksFromData(List<NetworkData> networkData){
            return networkData
                    .stream()
                    .map(network -> new Cobertura(
                            IP.fromAddress(network.getIp().getAddress()),
                            network.getName(),
                            network.getCidr()))
                    .collect(Collectors.toList());
        }

        private static List<NetworkData> getNetworksFromDomain(List<Cobertura> networks, UUID switchId){
            return  networks
                     .stream()
                     .map(network -> new NetworkData(
                            switchId,
                            IPData.fromAddress(network.getAddress().getIPAddress()),
                            network.getName(),
                            network.getCidr()))
                     .collect(Collectors.toList());
        }
}
