package dev.com.domain.entity;

import java.util.List;
import java.util.function.Predicate;

import dev.com.domain.vo.*;

public class Prima {

    private final PrimaType routerType;
    private final PrimaId routerId;
    private Plan networkSwitch;

    public Prima(PrimaType routerType, PrimaId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Prima(PrimaType routerType, PrimaId routerId, Plan networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public static Predicate<Prima> filterRouterByType(PrimaType routerType){
        return routerType.equals(PrimaType.CORE)
                ? Prima.isCore() :
                Prima.isEdge();
    }

    public static Predicate<Prima> isCore(){
        return p -> p.getRouterType() == PrimaType.CORE;
    }

    public static Predicate<Prima> isEdge(){
        return p -> p.getRouterType() == PrimaType.EDGE;
    }

    public void addNetworkToSwitch(Cobertura network){
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Cobertura createNetwork(IP address, String name, int cidr){
        return new Cobertura(address, name, cidr);
    }

    public List<Cobertura> retrieveNetworks(){
        return networkSwitch.getCoberturas();
    }

    public PrimaType getRouterType(){
        return routerType;
    }

    public PrimaId getRouterId() {
        return routerId;
    }

    @Override
    public String toString() {
        return "Prima{" +
                "primaType=" + routerType +
                ", primaId=" + routerId +
                ", coberturaSwitch=" + networkSwitch +
                '}';
    }
}