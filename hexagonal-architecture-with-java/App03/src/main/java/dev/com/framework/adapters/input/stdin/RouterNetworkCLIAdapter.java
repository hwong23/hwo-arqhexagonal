package dev.com.framework.adapters.input.stdin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.com.application.ports.input.RouterNetworkInputPort;
import dev.com.application.usecases.RouterNetworkUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;
import dev.com.framework.adapters.output.file.RouterNetworkFileAdapter;

public class RouterNetworkCLIAdapter {

    private RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCLIAdapter(){
        setAdapters();
    }

    private void setAdapters(){
        this.routerNetworkUseCase = new RouterNetworkInputPort(
            RouterNetworkFileAdapter.getInstance());
    }
    
    
    public Prima addNetwork(PrimaId routerId, Cobertura network){
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }
}