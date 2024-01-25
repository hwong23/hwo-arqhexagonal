package dev.mimutual.domain.specification;

import dev.mimutual.domain.entity.Router;
import dev.mimutual.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
