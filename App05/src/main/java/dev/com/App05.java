package dev.com;

import com.sun.net.httpserver.HttpServer;

import dev.com.application.funcionalidad.input.PrimaCoberturaInputPort;
import dev.com.application.funcionalidad.output.NotifyEventOutputPort;
import dev.com.application.funcionalidad.output.PrimaCoberturaOutputPort;
import dev.com.application.usecases.PrimaCoberturaUseCase;
import dev.com.framework.adapters.input.PrimaCoberturaAdapter;
import dev.com.framework.adapters.input.rest.PrimaCoberturaRestAdapter;
import dev.com.framework.adapters.input.stdin.PrimaCoberturaCLIAdapter;
import dev.com.framework.adapters.input.websocket.NotifyEventWebSocketAdapter;
import dev.com.framework.adapters.output.file.RouterNetworkFileAdapter;
import dev.com.framework.adapters.output.h2.PrimaCoberturaH2Adapter;
import dev.com.framework.adapters.output.kafka.NotifyEventKafkaAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class App05 {

    private PrimaCoberturaAdapter inputAdapter;
    private PrimaCoberturaUseCase usecase;
    private PrimaCoberturaOutputPort primaOutputPort;
    private NotifyEventOutputPort notifyOutputPort;

    public static void main(String... args) throws IOException, InterruptedException {
        var adapter = "cli";
        if(args.length>0) {
            adapter = args[0];
        }
        new App05().setAdapter(adapter);
    }

    void setAdapter(String adapter) throws IOException, InterruptedException {
        switch (adapter) {
            case "rest" -> {
                primaOutputPort = PrimaCoberturaH2Adapter.getInstance();
                notifyOutputPort = NotifyEventKafkaAdapter.getInstance();
                usecase = new PrimaCoberturaInputPort(primaOutputPort, notifyOutputPort);
                inputAdapter = new PrimaCoberturaRestAdapter(usecase);
                rest();
                
                NotifyEventWebSocketAdapter.startServer();
            }
            default -> {
                primaOutputPort = RouterNetworkFileAdapter.getInstance();
                usecase = new PrimaCoberturaInputPort(primaOutputPort);
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