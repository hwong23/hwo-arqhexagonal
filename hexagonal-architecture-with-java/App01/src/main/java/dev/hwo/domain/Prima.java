package dev.hwo.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.davivieira.domain.ValorCuota;


public class Prima {
    private final ValorCuota cuotaPrima;

    public Prima ( double cuota ) {
        valorCouta = cuota;
    }

    public ValorCuota verCuota () {
        return cuotaPrima;
    }

    @Override
    public String toString() {
        return "Prima {" +
                "id=" + "0x2FD" +
                ", cuota=" + valorCouta + '}';
    }
}
