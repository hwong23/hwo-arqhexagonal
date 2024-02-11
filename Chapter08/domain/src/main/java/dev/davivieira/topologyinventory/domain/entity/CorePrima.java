package dev.davivieira.topologyinventory.domain.entity;

import dev.davivieira.topologyinventory.domain.specification.PrimaNulaSpec;
import dev.davivieira.topologyinventory.domain.specification.PlanNuloSpec;
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

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public final class CorePrima extends Prima {

    @Setter
    private Map<Id, Prima> routers;

    @Builder
    public CorePrima(Id id, Vendor vendor, Model model, IP ip, Location location, PrimaType routerType, Map<Id, Prima> routers) {
        super(id, vendor, model, ip, location, routerType);
        this.routers = routers == null ? new HashMap<Id, Prima>() : routers;
    }

    public CorePrima addRouter(Prima anyRouter) {
        var sameCountryRouterSpec = new SameCountrySpec(this);
        var sameIpSpec = new SameIpSpec(this);

        sameCountryRouterSpec.check(anyRouter);
        sameIpSpec.check(anyRouter);

        this.routers.put(anyRouter.id, anyRouter);

        return this;
    }

    public Prima removeRouter(Prima anyRouter) {
        var emptyRoutersSpec = new PrimaNulaSpec();
        var emptySwitchSpec = new PlanNuloSpec();

        switch (anyRouter.routerType) {
            case CORE:
                var coreRouter = (CorePrima)anyRouter;
                emptyRoutersSpec.check(coreRouter);
                break;
            case EDGE:
                var edgeRouter = (EdgePrima)anyRouter;
                emptySwitchSpec.check(edgeRouter);
        }
        return this.routers.remove(anyRouter.id);
    }
}
