package dev.com;

import com.sun.net.httpserver.HttpServer;

import dev.com.application.ports.input.PrimaCoberturaInputPort;
import dev.com.application.ports.output.NotifyEventOutputPort;
import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.application.usecases.RouterNetworkUseCase;
import dev.com.framework.adapters.input.RouterNetworkAdapter;
import dev.com.framework.adapters.input.rest.PrimaCoberturaRestAdapter;
import dev.com.framework.adapters.input.stdin.RouterNetworkCLIAdapter;
import dev.com.framework.adapters.input.websocket.NotifyEventWebSocketAdapter;
import dev.com.framework.adapters.output.file.RouterNetworkFileAdapter;
import dev.com.framework.adapters.output.h2.PrimaCoberturaH2Adapter;
import dev.com.framework.adapters.output.kafka.NotifyEventKafkaAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class App {

    private RouterNetworkAdapter inputAdapter;
    private RouterNetworkUseCase usecase;
    private RouterNetworkOutputPort routerOutputPort;
    private NotifyEventOutputPort notifyOutputPort;

    public static void main(String... args) throws IOException, InterruptedException {
        var adapter = "cli";
        if(args.length>0) {
            adapter = args[0];
        }
        new App().setAdapter(adapter);
    }

    void setAdapter(String adapter) throws IOException, InterruptedException {
        switch (adapter) {
            case "rest" -> {
                routerOutputPort = PrimaCoberturaH2Adapter.getInstance();
                notifyOutputPort = NotifyEventKafkaAdapter.getInstance();
                usecase = new PrimaCoberturaInputPort(routerOutputPort, notifyOutputPort);
                inputAdapter = new PrimaCoberturaRestAdapter(usecase);
                rest();
                NotifyEventWebSocketAdapter.startServer();
            }
            default -> {
                routerOutputPort = RouterNetworkFileAdapter.getInstance();
                usecase = new PrimaCoberturaInputPort(routerOutputPort);
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