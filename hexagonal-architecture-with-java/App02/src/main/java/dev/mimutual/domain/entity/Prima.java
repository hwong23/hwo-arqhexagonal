package dev.mimutual.domain.entity;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.domain.vo.IP;
import dev.mimutual.domain.vo.Network;
import dev.mimutual.domain.vo.PrimaId;
import dev.mimutual.domain.vo.PrimaType;

public class Prima {

    private final PrimaType primaType;
    private final PrimaId primaId;
    private Plan networkSwitch;

    public Prima(PrimaType routerType, PrimaId routerId) {
        this.primaType = routerType;
        this.primaId = routerId;
    }

    public static Predicate<Prima> filterPrimaByType (PrimaType routerType) {
        return routerType.equals(PrimaType.CORE)
                ? Prima.isCore() :
                Prima.isEdge();
    }

    public static Predicate<Prima> isCore(){
        return p -> p.getPrimaType() == PrimaType.CORE;
    }

    public static Predicate<Prima> isEdge(){
        return p -> p.getPrimaType() == PrimaType.EDGE;
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

    public PrimaType getPrimaType(){
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