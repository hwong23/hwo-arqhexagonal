package dev.mimutual.domain.specification;

import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.PrimaType;

public final class RouterTypeSpecification extends AbstractSpecification<Prima> {

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.getPrimaType().equals(PrimaType.EDGE) || router.getPrimaType().equals(PrimaType.CORE);
    }
}
