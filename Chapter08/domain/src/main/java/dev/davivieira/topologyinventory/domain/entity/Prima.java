package dev.davivieira.topologyinventory.domain.entity;

import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Location;
import dev.davivieira.topologyinventory.domain.vo.Model;
import dev.davivieira.topologyinventory.domain.vo.PrimaType;
import dev.davivieira.topologyinventory.domain.vo.Vendor;
import lombok.Getter;

import java.util.function.Predicate;

@Getter
public abstract sealed class Prima extends Equipment permits CorePrima, EdgePrima {

    protected final PrimaType routerType;

    public static Predicate<Equipment> getRouterTypePredicate(PrimaType routerType){
        return r -> ((Prima)r).getRouterType().equals(routerType);
    }

    public static Predicate<Equipment> getModelPredicate(Model model){
        return r -> r.getModel().equals(model);
    }

    public static Predicate<Equipment> getCountryPredicate(Location location){
        return p -> p.location.country().equals(location.country());
    }

    public Prima(Id id, Vendor vendor, Model model, IP ip, Location location, PrimaType routerType) {
        super(id, vendor, model, ip, location);
        this.routerType = routerType;
    }
}