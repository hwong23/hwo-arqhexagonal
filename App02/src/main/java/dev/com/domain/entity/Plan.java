package dev.com.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.PlanId;
import dev.com.domain.vo.PlanType;

public class Plan {

    private final PlanType planType;
    private final PlanId planId;
    private final List<Cobertura> networks;
    private final IP address;

    public Plan (PlanType switchType, PlanId switchId, List<Cobertura> networks, IP address){
        this.planType = switchType;
        this.planId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Plan addNetwork(Cobertura network, Prima router) {
        List<Cobertura> newNetworks = new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);

        return new Plan(this.planType, this.planId, newNetworks, this.address);
    }

    public List<Cobertura> getNetworks() {
        return networks;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planType=" + planType +
                ", planId=" + planId +
                ", networks=" + networks +
                ", address=" + address +
                '}';
    }
}
