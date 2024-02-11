package dev.davivieira.topologyinventory.domain.entity;

import dev.davivieira.topologyinventory.domain.specification.CoberturaNulaSpec;
import dev.davivieira.topologyinventory.domain.specification.SameCountrySpec;
import dev.davivieira.topologyinventory.domain.specification.SameIpSpec;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Location;
import dev.davivieira.topologyinventory.domain.vo.Model;
import dev.davivieira.topologyinventory.domain.vo.PrimaType;
import dev.davivieira.topologyinventory.domain.vo.Vendor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public final class EdgePrima extends Prima {

    @Setter
    private Map<Id, Plan> switches;

    @Builder
    public EdgePrima(Id id, Vendor vendor, Model model, IP ip, Location location, PrimaType routerType, Map<Id, Plan> switches) {
        super(id, vendor, model, ip, location, routerType);
        this.switches = switches;
    }

    public void addSwitch(Plan anySwitch) {
        var sameCountryRouterSpec = new SameCountrySpec(this);
        var sameIpSpec = new SameIpSpec(this);

        sameCountryRouterSpec.check(anySwitch);
        sameIpSpec.check(anySwitch);

        this.switches.put(anySwitch.id,anySwitch);
    }

    public Plan removeSwitch(Plan anySwitch) {
        var emptyNetworkSpec = new CoberturaNulaSpec();
        emptyNetworkSpec.check(anySwitch);

        return this.switches.remove(anySwitch.id);
    }
}