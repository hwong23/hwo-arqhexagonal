package dev.mimutual.domain.service;

import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.specification.CIDRSpecification;
import dev.mimutual.domain.specification.NetworkAmountSpecification;
import dev.mimutual.domain.specification.NetworkAvailabilitySpecification;
import dev.mimutual.domain.specification.RouterTypeSpecification;
import dev.mimutual.domain.vo.Cobertura;

public class NetworkOperation {

    public static Prima createNewNetwork(Prima router, Cobertura network) {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.address(), network.name(), network.cidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var amountSpec = new NetworkAmountSpecification();

        if(cidrSpec.isSatisfiedBy(network.cidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);

        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");

        if(amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Cobertura newNetwork = router.createNetwork(network.address(), network.name(), network.cidr());
            router.addNetworkToSwitch(newNetwork);
        }
        return router;
    }
}
