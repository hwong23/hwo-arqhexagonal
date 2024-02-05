package dev.com.domain.specification;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Prima> {

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
