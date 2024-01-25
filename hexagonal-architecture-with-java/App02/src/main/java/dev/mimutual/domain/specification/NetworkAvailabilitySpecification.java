package dev.mimutual.domain.specification;

import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.IP;

public final class NetworkAvailabilitySpecification extends AbstractSpecification<Prima> {

    private final IP address;
    private final String name;
    private final int cidr;

    public NetworkAvailabilitySpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Prima router) {
        return router!=null && isNetworkAvailable(router);
    }

    private boolean isNetworkAvailable(Prima router) {
        return router.retrieveNetworks().stream().noneMatch(
                network -> network.address().equals(address) &&
                network.name().equals(name) &&
                network.cidr() == cidr);
    }
}
