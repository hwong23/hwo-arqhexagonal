package dev.mimutual.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.mimutual.domain.vo.IP;
import dev.mimutual.domain.vo.Cobertura;
import dev.mimutual.domain.vo.PlanId;
import dev.mimutual.domain.vo.PlanType;

public class Plan {

    private final PlanType switchType;
    private final PlanId switchId;
    private final List<Cobertura> networks;
    private final IP address;

    public Plan (PlanType switchType, PlanId switchId, List<Cobertura> networks, IP address){
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Plan addNetwork(Cobertura network, Prima router) {
        List<Cobertura> newNetworks = new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);

        return new Plan(this.switchType, this.switchId, newNetworks, this.address);
    }

    public List<Cobertura> getNetworks() {
        return networks;
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
