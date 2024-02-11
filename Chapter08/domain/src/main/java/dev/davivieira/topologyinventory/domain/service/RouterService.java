package dev.davivieira.topologyinventory.domain.service;

import dev.davivieira.topologyinventory.domain.entity.Equipment;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.vo.Id;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RouterService {

    public static List<Prima> filterAndRetrieveRouter(List<Prima> routers, Predicate<Equipment> routerPredicate){
        return routers
                .stream()
                .filter(routerPredicate)
                .collect(Collectors.<Prima>toList());
    }

    public static Prima findById(Map<Id,Prima> routers, Id id){
        return routers.get(id);
    }
}
