package dev.com.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.vo.IP;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.SwitchId;
import dev.com.domain.vo.SwitchType;

public class Switch {

    private final SwitchType switchType;
    private final SwitchId switchId;
    private final List<Network> networks;
    private final IP address;

    public Switch (SwitchType switchType, SwitchId switchId, List<Network> networks, IP address){
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network, Prima router) {
        List<Network> newNetworks = new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);

        return new Switch(this.switchType, this.switchId, newNetworks, this.address);
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
