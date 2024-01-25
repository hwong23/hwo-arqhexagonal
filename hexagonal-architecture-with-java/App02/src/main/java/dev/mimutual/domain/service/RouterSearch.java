package dev.mimutual.domain.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.mimutual.domain.entity.Prima;

public class RouterSearch {

    public static List<Prima> retrieveRouter(List<Prima> routers, Predicate<Prima> predicate) {
        return routers.stream()
                .filter(predicate)
                .collect(Collectors.<Prima>toList());
    }
}
