package dev.com.domain.entity;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.SwitchId;
import dev.com.domain.vo.SwitchType;

public class Switch {

    private SwitchId switchId;
    private SwitchType switchType;
    private List<Cobertura> networks;
    private IP address;

    public Switch (SwitchId switchId, SwitchType switchType, List<Cobertura> networks, IP address){
        this.switchId = switchId;
        this.switchType = switchType;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Cobertura network, Prima router){
        List<Cobertura> newNetworks = new ArrayList<>();

        router.retrieveNetworks().forEach(net ->{
            newNetworks.add(net);
        });

        newNetworks.add(network);
        return new Switch(this.switchId, this.switchType, newNetworks, this.address);
    }

    public List<Cobertura> getNetworks() {
        return networks;
    }

    public SwitchId getSwitchId() {
        return switchId;
    }

    public SwitchType getSwitchType() {
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
