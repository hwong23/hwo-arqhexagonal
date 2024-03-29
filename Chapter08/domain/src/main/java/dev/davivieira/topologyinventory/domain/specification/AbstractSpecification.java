package dev.davivieira.topologyinventory.domain.specification;

import dev.davivieira.topologyinventory.domain.exception.GenericSpecificationException;

public abstract sealed class AbstractSpecification<T> implements Specification<T> permits
        AndSpecification,
        CIDRSpecification,
        CoberturaNulaSpec,
        PrimaNulaSpec,
        PlanNuloSpec,
        NetworkAmountSpec,
        NetworkAvailabilitySpec,
        SameCountrySpec,
        SameIpSpec
{

    public abstract boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws GenericSpecificationException;

    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }
}
