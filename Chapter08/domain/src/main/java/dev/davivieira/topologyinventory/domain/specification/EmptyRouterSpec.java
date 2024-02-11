package dev.davivieira.topologyinventory.domain.specification;

import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.exception.GenericSpecificationException;

public final class EmptyRouterSpec extends AbstractSpecification<CorePrima> {

    @Override
    public boolean isSatisfiedBy(CorePrima coreRouter) {
        return coreRouter.getRouters()==null||
                coreRouter.getRouters().isEmpty();
    }

    @Override
    public void check(CorePrima coreRouter) {
        if(!isSatisfiedBy(coreRouter))
            throw new GenericSpecificationException("It isn't allowed to remove a core router with other routers attached to it");
    }

}
