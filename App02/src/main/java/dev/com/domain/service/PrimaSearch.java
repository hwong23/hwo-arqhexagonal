package dev.com.domain.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.com.domain.entity.Prima;

public class PrimaSearch {

    // retrieve method can now be consumed as a service 
    // by other objects in the domain 
    // and in other hexagons
    public static List<Prima> retrievePrima(List<Prima> primas, Predicate<Prima> predicate) {
        return primas.stream()
                .filter(predicate)
                .collect(Collectors.<Prima>toList());
    }
}
