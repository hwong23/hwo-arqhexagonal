package dev.com.domain.specification;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaType;

public final class RouterTypeSpecification extends AbstractSpecification<Prima> {

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.getPrimaType().equals(PrimaType.EDGE) || router.getPrimaType().equals(PrimaType.CORE);
    }
}
