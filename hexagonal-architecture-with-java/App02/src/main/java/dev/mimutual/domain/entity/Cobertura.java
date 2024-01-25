package dev.mimutual.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.mimutual.domain.vo.IP;
import dev.mimutual.domain.vo.Network;
import dev.mimutual.domain.vo.SwitchId;
import dev.mimutual.domain.vo.SwitchType;

public class Cobertura {

    private final SwitchType switchType;
    private final SwitchId switchId;
    private final List<Network> networks;
    private final IP address;

    public Cobertura (SwitchType switchType, SwitchId switchId, List<Network> networks, IP address){
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Cobertura addNetwork(Network network, Prima router) {
        List<Network> newNetworks = new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);

        return new Cobertura(this.switchType, this.switchId, newNetworks, this.address);
    }

    public List<Network> getNetworks() {
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
