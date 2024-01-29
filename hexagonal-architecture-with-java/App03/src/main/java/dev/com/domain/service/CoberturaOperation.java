package dev.com.domain.service;

import dev.com.domain.entity.Prima;
import dev.com.domain.specification.CIDRSpecification;
import dev.com.domain.specification.NetworkAmountSpecification;
import dev.com.domain.specification.NetworkAvailabilitySpecification;
import dev.com.domain.specification.RouterTypeSpecification;
import dev.com.domain.vo.Cobertura;

public class CoberturaOperation {

    public static Prima createNewCobertura(Prima prima, Cobertura network) {
        var availabilitySpec = new NetworkAvailabilitySpecification(
            network.getAddress(), network.getName(), network.getCidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var amountSpec = new NetworkAmountSpecification();

        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);

        if(!availabilitySpec.isSatisfiedBy(prima))
            throw new IllegalArgumentException("Address already exist");

        if(amountSpec.and(routerTypeSpec).isSatisfiedBy(prima)) {
            Cobertura newNetwork = prima.createNetwork(network.getAddress(), network.getName(), network.getCidr());
            prima.addNetworkToSwitch(newNetwork);
        }
        return prima;
    }
}
