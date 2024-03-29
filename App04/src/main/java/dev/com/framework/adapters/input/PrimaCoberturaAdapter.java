package dev.com.framework.adapters.input;

import java.util.Map;

import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

public abstract class PrimaCoberturaAdapter {

    protected Prima router;
    protected PrimaCoberturaUseCase routerNetworkUseCase;

    protected Prima addNetworkToRouter(Map<String, String> params){
        var routerId = PrimaId.withId(params.get("routerId"));
        var network = new Cobertura(IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Prima processRequest(Object requestParams);
}
