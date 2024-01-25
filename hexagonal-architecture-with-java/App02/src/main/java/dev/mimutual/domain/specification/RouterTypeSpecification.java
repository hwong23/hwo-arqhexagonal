package dev.mimutual.domain.specification;

import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Prima> {

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
