package dev.mimutual.domain.entity;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.domain.vo.IP;
import dev.mimutual.domain.vo.Network;
import dev.mimutual.domain.vo.RouterId;
import dev.mimutual.domain.vo.RouterType;

public class Prima {

    private final RouterType primaType;
    private final RouterId primaId;
    private Plan networkSwitch;

    public Prima(RouterType routerType, RouterId routerId) {
        this.primaType = routerType;
        this.primaId = routerId;
    }

    public static Predicate<Prima> filterPrimaByType (RouterType routerType) {
        return routerType.equals(RouterType.CORE)
                ? Prima.isCore() :
                Prima.isEdge();
    }

    public static Predicate<Prima> isCore(){
        return p -> p.getPrimaType() == RouterType.CORE;
    }

    public static Predicate<Prima> isEdge(){
        return p -> p.getPrimaType() == RouterType.EDGE;
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

    public RouterType getPrimaType(){
        return primaType;
    }

    @Override
    public String toString() {
        return "Prima{" +
                "primaType=" + primaType +
                ", primaId=" + primaId +
                '}';
    }
}