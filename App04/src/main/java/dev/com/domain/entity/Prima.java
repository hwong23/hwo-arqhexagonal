package dev.com.domain.entity;

import java.util.List;

import dev.com.domain.vo.*;

public class Prima {

    private PrimaType routerType;
    private PrimaId routerId;
    private Plan networkSwitch;

    public Prima(){

    }

    public Prima(PrimaType routerType, PrimaId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Prima(PrimaType routerType, PrimaId routerId, Plan networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public boolean isType(PrimaType type){
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

    public PrimaType getRouterType() {
        return routerType;
    }

    public PrimaId getRouterId() {
        return routerId;
    }

    public Plan getNetworkSwitch() {
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