package dev.davivieira.topologyinventory.domain.service;

import dev.davivieira.topologyinventory.domain.vo.Cobertura;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CoberturaService {

    public static List<Cobertura> filterAndRetrieveNetworks(List<Cobertura> networks, Predicate<Cobertura> networkPredicate){
        return networks
                .stream()
                .filter(networkPredicate)
                .collect(Collectors.<Cobertura>toList());
    }

    public static Cobertura findNetwork(List<Cobertura> networks, Predicate<Cobertura> networkPredicate){
        return networks
                .stream()
                .filter(networkPredicate)
                .findFirst().orElse(null);
    }
}
