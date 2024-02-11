package dev.davivieira.topologyinventory.domain.specification;

import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.exception.GenericSpecificationException;

public final class CoberturaNulaSpec extends AbstractSpecification<Plan> {

    @Override
    public boolean isSatisfiedBy(Plan switchNetwork) {
        return switchNetwork.getSwitchNetworks()==null||
                switchNetwork.getSwitchNetworks().isEmpty();
    }

    @Override
    public void check(Plan aSwitch) throws GenericSpecificationException {
        if(!isSatisfiedBy(aSwitch))
            throw new GenericSpecificationException("It's not possible to remove a switch with networks attached to it");
    }
}
