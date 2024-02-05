package dev.com.domain.entity;

import java.util.List;
import java.util.function.Predicate;

import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.PrimaId;
import dev.com.domain.vo.PrimaType;

public class Prima {

    private final PrimaType primaType;
    private final PrimaId primaId;
    private Plan planSwitch;

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

    public void addNetworkToSwitch(Cobertura network){
        this.planSwitch = planSwitch.addNetwork(network, this);
    }

    public Cobertura createNetwork(IP address, String name, int cidr){
        return new Cobertura(address, name, cidr);
    }

    public List<Cobertura> retrieveNetworks(){
        return planSwitch.getNetworks();
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