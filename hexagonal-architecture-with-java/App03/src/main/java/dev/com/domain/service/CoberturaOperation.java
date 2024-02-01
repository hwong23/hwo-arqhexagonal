package dev.com.domain.service;

import dev.com.domain.entity.Prima;
import dev.com.domain.specification.CIDRSpecification;
import dev.com.domain.specification.CoberturaAmountSpecification;
import dev.com.domain.specification.CoberturaAvailabilitySpecification;
import dev.com.domain.specification.PrimaTypeSpecification;
import dev.com.domain.vo.Cobertura;

public class CoberturaOperation {

    public static Prima createNewCobertura(Prima prima, Cobertura cobertura) {
        var availabilitySpec = new CoberturaAvailabilitySpecification(
            cobertura.getAddress(), cobertura.getName(), cobertura.getCidr());
        var cidrSpec = new CIDRSpecification();
        var primaTypeSpec = new PrimaTypeSpecification();
        var amountSpec = new CoberturaAmountSpecification();

        if(cidrSpec.isSatisfiedBy(cobertura.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);

        if(!availabilitySpec.isSatisfiedBy(prima))
            throw new IllegalArgumentException("Address already exist");

        if(amountSpec.and(primaTypeSpec).isSatisfiedBy(prima)) {
            Cobertura newNetwork = prima.createNetwork(cobertura.getAddress(), cobertura.getName(), cobertura.getCidr());
            prima.addCoberturaToPlan(newNetwork);
        }
        
        return prima;
    }
}
