package dev.com;

import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;
import dev.com.framework.adapters.input.stdin.RouterNetworkCLIAdapter;

public class App {

    public static void main(String... args) {
        var cli = new RouterNetworkCLIAdapter();
        var routerId = PrimaId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Cobertura(new IP("20.0.0.0"), "Marketing", 8);
        var router = cli.addNetwork(routerId, network);
        System.out.println(router);
    }
}