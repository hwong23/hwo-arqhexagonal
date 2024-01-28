package dev.com.framework.adapters.input.stdin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.com.application.ports.input.RouterNetworkInputPort;
import dev.com.application.usecases.RouterNetworkUseCase;
import dev.com.domain.entity.Router;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;
import dev.com.framework.adapters.output.file.RouterNetworkFileAdapter;

public class RouterNetworkCLIAdapter {

    private RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCLIAdapter(){
        setAdapters();
    }

    public Router addNetwork(RouterId routerId, Network network){
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    private void setAdapters(){
        this.routerNetworkUseCase = new RouterNetworkInputPort(RouterNetworkFileAdapter.getInstance());
    }

}
