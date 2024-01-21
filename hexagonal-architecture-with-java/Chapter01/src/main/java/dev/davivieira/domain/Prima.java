package dev.davivieira.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Prima {
    private final double valorCouta;

    public Prima ( double cuota ) {
        valorCouta = cuota;
    }

    
    @Override
    public String toString() {
        return "Prima {" +
                "id=" + "0x2FD" +
                ", cuota=" + valorCouta + '}';
    }
}
