package dev.com.framework.adapters.input;

import java.util.Map;

import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

public abstract class PrimaCoberturaAdapter {

    protected Prima prima;
    protected PrimaCoberturaUseCase primaCoberturaUseCase;

    protected Prima addCoberturaToPrima(Map<String, String> params)
    {
        var primaId = PrimaId.withId(params.get("routerId"));
        var cobertura = new Cobertura(IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));

        return primaCoberturaUseCase.addCoberturaToPrima(primaId, cobertura);
    }

    public abstract Prima processRequest(Object requestParams);
}
