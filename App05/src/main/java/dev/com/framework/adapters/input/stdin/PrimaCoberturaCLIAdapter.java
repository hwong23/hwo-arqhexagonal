package dev.com.framework.adapters.input.stdin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.framework.adapters.input.PrimaCoberturaAdapter;
import dev.com.framework.adapters.output.file.mappers.RouterJsonFileMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PrimaCoberturaCLIAdapter extends PrimaCoberturaAdapter {
    
    public PrimaCoberturaCLIAdapter(PrimaCoberturaUseCase routerNetworkUseCase){
        this.routerNetworkUseCase = routerNetworkUseCase;
    }

    @Override
    public Prima processRequest(Object requestParams){
        
        var params = stdinParams(requestParams);
        
        // mapeo externo a dominio
        prima = this.addCoberturaToPrima(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            var routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(prima));
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return prima;
    }

    private Map<String, String> stdinParams(Object requestParams){
        Map<String, String> params = new HashMap<>();
        if(requestParams instanceof Scanner){
            var scanner = (Scanner) requestParams;
            System.out.println("Por favor informe la Prima ID:");
            var routerId = scanner.nextLine();
            params.put("routerId", routerId);
            System.out.println("Informe la direcicón IP:");
            var address = scanner.nextLine();
            params.put("address", address);
            System.out.println("Informe nombre de Cobertura:");
            var name = scanner.nextLine();
            params.put("name", name);
            System.out.println("Por fabvor informe the CIDR:");
            var cidr = scanner.nextLine();
            params.put("cidr", cidr);
        }
        return params;
    }
}
