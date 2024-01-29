package dev.com.framework.adapters.input;

import java.util.Map;

import dev.com.application.usecases.RouterNetworkUseCase;
import dev.com.domain.entity.Router;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public abstract class RouterNetworkAdapter {

    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;

    protected Router addNetworkToRouter(Map<String, String> params){
        var routerId = RouterId.withId(params.get("routerId"));
        var network = new Network(IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Router processRequest(Object requestParams);
}
