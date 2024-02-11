package dev.com.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PlanId;
import dev.com.domain.vo.PlanType;

public class Plan {

    private PlanId switchId;
    private PlanType switchType;
    private List<Cobertura> networks;
    private IP address;

    public Plan (PlanId switchId, PlanType switchType, List<Cobertura> networks, IP address){
        this.switchId = switchId;
        this.switchType = switchType;
        this.networks = networks;
        this.address = address;
    }

    public Plan addNetwork(Cobertura network, Prima router){
        List<Cobertura> newNetworks = new ArrayList<>();

        router.retrieveNetworks().forEach(net ->{
            newNetworks.add(net);
        });

        newNetworks.add(network);
        return new Plan(this.switchId, this.switchType, newNetworks, this.address);
    }

    public List<Cobertura> getNetworks() {
        return networks;
    }

    public PlanId getSwitchId() {
        return switchId;
    }

    public PlanType getSwitchType() {
        return switchType;
    }

    public IP getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "switchType=" + switchType +
                ", switchId=" + switchId +
                ", networks=" + networks +
                ", address=" + address +
                '}';
    }
}
