package dev.davivieira.topologyinventory.domain.specification;

import dev.davivieira.topologyinventory.domain.entity.EdgePrima;
import dev.davivieira.topologyinventory.domain.exception.GenericSpecificationException;

public final class EmptySwitchSpec extends AbstractSpecification<EdgePrima> {

    @Override
    public boolean isSatisfiedBy(EdgePrima edgeRouter) {
        return edgeRouter.getSwitches()==null ||
                edgeRouter.getSwitches().isEmpty();
    }

    @Override
    public void check(EdgePrima edgeRouter) {
        if(!isSatisfiedBy(edgeRouter))
            throw new GenericSpecificationException("It isn't allowed to remove an edge router with a switch attached to it");
    }
}
