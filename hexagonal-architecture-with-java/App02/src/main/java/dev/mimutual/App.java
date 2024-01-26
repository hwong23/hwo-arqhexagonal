package dev.mimutual;

import dev.mimutual.framework.adapters.input.stdin.PrimaViewCLIAdapter;

public class App {

    public static void main(String... args) {
        var cli = new PrimaViewCLIAdapter();
        var type = "CORE";

        System.out.println( "App02.Referencia");
        System.out.println(cli.obtainRelatedPrimas(type));

    }
}