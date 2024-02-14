package dev.com;

import com.sun.net.httpserver.HttpServer;

import dev.com.application.funcionalidad.entrada.PrimaCoberturaInputFuncionalidad;
import dev.com.application.funcionalidad.salida.PrimaFuncionalidadOutputFuncionalidad;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.framework.adapters.input.RouterNetworkAdapter;
import dev.com.framework.adapters.input.rest.RouterNetworkRestAdapter;
import dev.com.framework.adapters.input.stdin.RouterNetworkCLIAdapter;
import dev.com.framework.adapters.output.file.RouterNetworkFileAdapter;
import dev.com.framework.adapters.output.h2.RouterNetworkH2Adapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class App {

    private RouterNetworkAdapter inputAdapter;
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
                inputAdapter = new RouterNetworkRestAdapter(usecase);
                rest();
            }
            default -> {
                outputPort = RouterNetworkFileAdapter.getInstance();
                usecase = new PrimaCoberturaInputFuncionalidad(outputPort);
                inputAdapter = new RouterNetworkCLIAdapter(usecase);
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