package dev.com.domain.entity;

import java.util.List;
import java.util.function.Predicate;

import dev.com.domain.vo.*;

public class Prima {

    private final RouterType routerType;
    private final RouterId routerId;
    private Switch networkSwitch;

    public Prima(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Prima(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public static Predicate<Prima> filterRouterByType(RouterType routerType){
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

    public RouterId getRouterId() {
        return routerId;
    }

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                ", networkSwitch=" + networkSwitch +
                '}';
    }
}