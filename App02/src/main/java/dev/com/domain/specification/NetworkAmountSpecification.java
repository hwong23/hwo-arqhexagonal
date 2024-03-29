package dev.com.domain.specification;

import dev.com.domain.entity.Prima;

public final class NetworkAmountSpecification extends AbstractSpecification<Prima> {

    final static public int MAXIMUM_ALLOWED_NETWORKS = 6;

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router.retrieveNetworks().size() <= MAXIMUM_ALLOWED_NETWORKS;
    }
}
