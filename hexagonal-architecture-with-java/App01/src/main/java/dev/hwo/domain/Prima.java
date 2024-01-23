package dev.hwo.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.hwo.domain.ValorCuota;


public class Prima {
    private final ValorCuota cuotaPrima;

    public Prima ( ValorCuota cuota ) {
        this.cuotaPrima = cuota;
    }

    public ValorCuota objCuota () {
        return cuotaPrima;
    }

    private static Predicate<Prima> esCorrecta() {
        return p -> p.objCuota() != null;
    }


    @Override
    public String toString() {
        return "Prima {" +
                "id=" + "0x2FD" +
                ", cuota=" + cuotaPrima.toString() + '}';
    }
}
