package dev.com.domain.service;

import dev.com.domain.entity.Prima;
import dev.com.domain.specification.CIDRSpecification;
import dev.com.domain.specification.CoberturaAmountSpecification;
import dev.com.domain.specification.CoberturaAvailabilitySpecification;
import dev.com.domain.specification.PrimaTypeSpecification;
import dev.com.domain.vo.Cobertura;

public class CoberturaOperation {

    public static Prima createNewNetwork(Prima router, Cobertura network) {
        var availabilitySpec = new CoberturaAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new PrimaTypeSpecification();
        var amountSpec = new CoberturaAmountSpecification();

        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);

        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");

        if(amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Cobertura newNetwork = router.createNetwork(network.getAddress(), network.getName(), network.getCidr());
            router.addNetworkToSwitch(newNetwork);
        }
        return router;
    }
}
