package dev.hwo;

import dev.hwo.framework.adapters.input.stdin.RouterViewCLIAdapter;

public class App {

    public static void main(String... args) {
        var cli = new RouterViewCLIAdapter();
        var type = "CORE";

        System.out.println("App1.Referencia");
        System.out.println(cli.obtainRelatedRouters(type));
    }
}