package dev.com;

import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;
import dev.com.framework.adapters.input.stdin.PrimaCoberturaCLIAdapter;

public class App03 {

    public static void main(String... args) {
        var cli = new PrimaCoberturaCLIAdapter();
        var primaId = PrimaId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var cobertura = new Cobertura(new IP("20.0.0.0"), "Marketing", 8);
        var prima = cli.addCobertura(primaId, cobertura);
        
        System.out.println(prima);
    }
}