package dev.com.framework.adapters.input.stdin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dev.com.application.ports.input.PrimaCoberturaInputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;
import dev.com.framework.adapters.output.file.RouterNetworkFileAdapter;

public class PrimaCoberturaCLIAdapter {

    private PrimaCoberturaUseCase primaCoberturaUseCase;

    public PrimaCoberturaCLIAdapter(){
        setAdapters();
    }

    private void setAdapters(){
        this.primaCoberturaUseCase = new PrimaCoberturaInputPort(
            RouterNetworkFileAdapter.getInstance());
    }
    
    
    public Prima addCobertura(PrimaId routerId, Cobertura cobertura){
        return primaCoberturaUseCase.addCoberturaToPrima(routerId, cobertura);
    }
}
