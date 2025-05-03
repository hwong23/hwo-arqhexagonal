package dev.com;

import dev.com.framework.adapters.input.stdin.PrimaViewCLIAdapter;

public class App02 {

    public static void main(String... args) {
        var cli = new PrimaViewCLIAdapter();
        var type = "CORE";

        System.out.println( "Desde devcontainer vsc-vscode-remote try java");
        System.out.println( "App02.Referencia");
        System.out.println(cli.obtainRelatedPrimas(type));

    }
}