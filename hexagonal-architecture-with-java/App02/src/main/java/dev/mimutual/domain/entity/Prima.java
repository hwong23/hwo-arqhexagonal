package dev.mimutual.domain.entity;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.domain.vo.IP;
import dev.mimutual.domain.vo.Network;
import dev.mimutual.domain.vo.RouterId;
import dev.mimutual.domain.vo.RouterType;

public class Prima {

    private final RouterType routerType;
    private final RouterId routerId;
    private Cobertura networkSwitch;

    public Prima(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public static Predicate<Prima> filterRouterByType (RouterType routerType) {
        return routerType.equals(RouterType.CORE)
                ? Prima.isCore() :
                Prima.isEdge();
    }

    public static Predicate<Prima> isCore(){
        return p -> p.getRouterType() == RouterType.CORE;
    }

    public static Predicate<Prima> isEdge(){
        return p -> p.getRouterType() == RouterType.EDGE;
    }

    public void addNetworkToSwitch(Network network){
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Network createNetwork(IP address, String name, int cidr){
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks(){
        return networkSwitch.getNetworks();
    }

    public RouterType getRouterType(){
        return routerType;
    }

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                '}';
    }
}