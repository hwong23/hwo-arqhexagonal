package dev.com.domain.entity;

import java.util.List;

import dev.com.domain.vo.*;

public class Prima {

    private RouterType routerType;
    private PrimaId routerId;
    private Switch networkSwitch;

    public Prima(){

    }

    public Prima(RouterType routerType, PrimaId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Prima(RouterType routerType, PrimaId routerId, Switch networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public boolean isType(RouterType type){
        return this.routerType == type;
    }

    public void addNetworkToSwitch(Cobertura network){
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Cobertura createNetwork(IP address, String name, int cidr){
        return new Cobertura(address, name, cidr);
    }

    public List<Cobertura> retrieveNetworks(){
        return networkSwitch.getNetworks();
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public PrimaId getRouterId() {
        return routerId;
    }

    public Switch getNetworkSwitch() {
        return networkSwitch;
    }

    @Override
    public String toString() {
        return "Router{" +
                "type=" + routerType +
                ", id=" + routerId +
                ", networkSwitch=" + networkSwitch +
                '}';
    }
}