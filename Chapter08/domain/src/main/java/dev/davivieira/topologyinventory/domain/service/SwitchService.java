package dev.davivieira.topologyinventory.domain.service;

import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.Id;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SwitchService {

    public static List<Plan> filterAndRetrieveSwitch(List<Plan> switches, Predicate<Plan> switchPredicate){
        return switches
                .stream()
                .filter(switchPredicate)
                .collect(Collectors.<Plan>toList());
    }

    public static Plan findById(Map<Id,Plan> switches, Id id){
        return switches.get(id);
    }
}
