package dev.com.domain.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.com.domain.entity.Prima;

public class PrimaSearch {

    public static List<Prima> retrievePrima(List<Prima> primas, Predicate<Prima> predicate) {
        return primas.stream()
                .filter(predicate)
                .collect(Collectors.<Prima>toList());
    }
}
