package dev.com.domain.specification;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaType;

public final class PrimaTypeSpecification extends AbstractSpecification<Prima> {

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.getRouterType().equals(PrimaType.EDGE) || router.getRouterType().equals(PrimaType.CORE);
    }
}
