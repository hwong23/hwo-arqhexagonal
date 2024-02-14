package dev.com;

import com.sun.net.httpserver.HttpServer;

import dev.com.application.funcionalidad.entrada.PrimaCoberturaInputFuncionalidad;
import dev.com.application.funcionalidad.salida.PrimaFuncionalidadOutputFuncionalidad;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.framework.adapters.input.PrimaCoberturaAdapter;
import dev.com.framework.adapters.input.rest.PrimaCoberturaRestAdapter;
import dev.com.framework.adapters.input.stdin.PrimaCoberturaCLIAdapter;
import dev.com.framework.adapters.output.file.PrimaCoberturaFileAdapter;
import dev.com.framework.adapters.output.h2.RouterNetworkH2Adapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class App {

    private PrimaCoberturaAdapter inputAdapter;
    private PrimaCoberturaUseCase usecase;
    private PrimaFuncionalidadOutputFuncionalidad outputPort;

    public static void main(String... args)  {
        var adapter = "cli";
        if(args.length>0) {
            adapter = args[0];
        }
        new App().setAdapter(adapter);
    }

    void setAdapter(String adapter) {
        switch (adapter) {
            case "rest" -> {
                outputPort = RouterNetworkH2Adapter.getInstance();
                usecase = new PrimaCoberturaInputFuncionalidad(outputPort);
                inputAdapter = new PrimaCoberturaRestAdapter(usecase);
                rest();
            }
            default -> {
                outputPort = PrimaCoberturaFileAdapter.getInstance();
                usecase = new PrimaCoberturaInputFuncionalidad(outputPort);
                inputAdapter = new PrimaCoberturaCLIAdapter(usecase);
                cli();
            }
        }
    }

    private void cli() {
        Scanner scanner = new Scanner(System.in);
        inputAdapter.processRequest(scanner);
    }

    private void rest() {
        try {
            System.out.println("REST endpoint listening on port 8090...");
            var httpserver = HttpServer.create(new InetSocketAddress(8090), 0);
            inputAdapter.processRequest(httpserver);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}