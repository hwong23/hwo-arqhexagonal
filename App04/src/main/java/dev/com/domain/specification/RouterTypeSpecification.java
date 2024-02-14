package dev.com.domain.specification;

import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
