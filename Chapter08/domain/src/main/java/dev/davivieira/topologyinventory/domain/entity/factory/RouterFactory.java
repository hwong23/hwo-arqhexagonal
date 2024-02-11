package dev.davivieira.topologyinventory.domain.entity.factory;

import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Id;
import dev.davivieira.topologyinventory.domain.vo.Location;
import dev.davivieira.topologyinventory.domain.vo.Model;
import dev.davivieira.topologyinventory.domain.vo.PrimaType;
import dev.davivieira.topologyinventory.domain.vo.Vendor;

public class RouterFactory {

    public static Prima getRouter(Id id,
                                   Vendor vendor,
                                   Model model,
                                   IP ip,
                                   Location location,
                                   PrimaType routerType){

        switch (routerType) {
            case CORE -> {
                return CorePrima.builder().
                        id(id == null ? Id.withoutId() : id).
                        vendor(vendor).
                        model(model).
                        ip(ip).
                        location(location).
                        routerType(routerType).
                        build();
            }
            case EDGE -> {
                return EdgePrima.builder().
                        id(id==null ? Id.withoutId():id).
                        vendor(vendor).
                        model(model).
                        ip(ip).
                        location(location).
                        routerType(routerType).
                        build();
            }
            default -> throw new UnsupportedOperationException( "No valid router type informed");
        }
    }
}
