package dev.davivieira.topologyinventory.domain.entity;

import dev.davivieira.topologyinventory.domain.specification.CIDRSpecification;
import dev.davivieira.topologyinventory.domain.specification.NetworkAmountSpec;
import dev.davivieira.topologyinventory.domain.specification.NetworkAvailabilitySpec;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Location;
import dev.davivieira.topologyinventory.domain.vo.Model;
import dev.davivieira.topologyinventory.domain.vo.Cobertura;
import dev.davivieira.topologyinventory.domain.vo.Protocol;
import dev.davivieira.topologyinventory.domain.vo.PlanType;
import dev.davivieira.topologyinventory.domain.vo.Vendor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Predicate;

@Getter
public final class Plan extends Equipment {

    private PlanType switchType;
    private List<Cobertura> switchNetworks;

    @Setter
    private Id routerId;

    @Builder
    public Plan(Id id, Id routerId, Vendor vendor, Model model, IP ip, Location location, PlanType switchType, List<Cobertura> switchNetworks){
        super(id, vendor, model, ip, location);
        this.switchType = switchType;
        this.switchNetworks = switchNetworks;
        this.routerId = routerId;
    }

    public static Predicate<Cobertura> getNetworkProtocolPredicate(Protocol protocol){
        return s -> s.getNetworkAddress().getProtocol().equals(protocol);
    }

    public static Predicate<Plan> getSwitchTypePredicate(PlanType switchType){
        return s -> s.switchType .equals(switchType);
    }

    public boolean addNetworkToSwitch(Cobertura network) {
        var availabilitySpec = new NetworkAvailabilitySpec(network);
        var cidrSpec = new CIDRSpecification();
        var amountSpec = new NetworkAmountSpec();

        cidrSpec.check(network.getNetworkCidr());
        availabilitySpec.check(this);
        amountSpec.check(this);

        return this.switchNetworks.add(network);
    }

    public boolean removeNetworkFromSwitch(Cobertura network){
        return this.switchNetworks.remove(network);
    }
}

