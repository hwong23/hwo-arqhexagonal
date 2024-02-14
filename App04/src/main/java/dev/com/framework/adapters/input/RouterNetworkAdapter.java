package dev.com.framework.adapters.input;

import java.util.Map;

import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public abstract class RouterNetworkAdapter {

    protected Prima router;
    protected PrimaCoberturaUseCase routerNetworkUseCase;

    protected Prima addNetworkToRouter(Map<String, String> params){
        var routerId = RouterId.withId(params.get("routerId"));
        var network = new Network(IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Prima processRequest(Object requestParams);
}
