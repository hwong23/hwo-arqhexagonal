package dev.com.domain.specification;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Cobertura;

public final class CoberturaAvailabilitySpecification 
    extends AbstractSpecification<Prima> {

    private final IP address;
    private final String name;
    private final int cidr;

    public CoberturaAvailabilitySpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Prima prima) {
        return prima!=null && isNetworkAvailable(prima);
    }

    private boolean isNetworkAvailable(Prima prima) {
        return prima.retrieveNetworks().stream().noneMatch(
                network -> network.address().equals(address) &&
                        network.name().equals(name) &&
                        network.cidr() == cidr);
    }
}
