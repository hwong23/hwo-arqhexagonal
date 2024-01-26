package dev.mimutual.domain.specification;

import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Prima> {

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.getPrimaType().equals(RouterType.EDGE) || router.getPrimaType().equals(RouterType.CORE);
    }
}
