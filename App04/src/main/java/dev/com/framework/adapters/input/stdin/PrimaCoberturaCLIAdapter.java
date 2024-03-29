package dev.com.framework.adapters.input.stdin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.framework.adapters.input.PrimaCoberturaAdapter;
import dev.com.framework.adapters.output.file.mappers.PrimaJsonFileMapper;

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
        router = this.addNetworkToRouter(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            var routerJson = mapper.writeValueAsString(PrimaJsonFileMapper.toJson(router));
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return router;
    }

    private Map<String, String> stdinParams(Object requestParams){
        Map<String, String> params = new HashMap<>();
        if(requestParams instanceof Scanner){
            var scanner = (Scanner) requestParams;
            System.out.println("Please inform the Router ID:");
            var routerId = scanner.nextLine();
            params.put("routerId", routerId);
            System.out.println("Please inform the IP address:");
            var address = scanner.nextLine();
            params.put("address", address);
            System.out.println("Please inform the Network Name:");
            var name = scanner.nextLine();
            params.put("name", name);
            System.out.println("Please inform the CIDR:");
            var cidr = scanner.nextLine();
            params.put("cidr", cidr);
        } else {
            throw new IllegalArgumentException("Request with invalid parameters");
        }
        return params;
    }
}
