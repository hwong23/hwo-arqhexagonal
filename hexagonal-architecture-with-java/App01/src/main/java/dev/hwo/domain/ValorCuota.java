package dev.hwo.domain;

public class ValorCuota {
    private final double cuota;

    public ValorCuota (valor) {
        this.cuota = valor;
    }

    @Override
    public String toString() {
        return "valorCuota {" +
                "valor=" + cuota +
                '}';
    }
}