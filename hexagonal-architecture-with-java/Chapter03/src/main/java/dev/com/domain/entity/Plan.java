package dev.com.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.SwitchId;
import dev.com.domain.vo.SwitchType;

public class Plan {

    private final SwitchType switchType;
    private final SwitchId switchId;
    private final List<Cobertura> networks;
    private final IP address;

    public Plan (SwitchType switchType, SwitchId switchId, List<Cobertura> networks, IP address){
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
