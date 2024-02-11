package dev.davivieira.topologyinventory.domain.specification;

import dev.davivieira.topologyinventory.domain.entity.Equipment;
import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.exception.GenericSpecificationException;
import dev.davivieira.topologyinventory.domain.vo.IP;
import dev.davivieira.topologyinventory.domain.vo.Cobertura;

public final class NetworkAvailabilitySpec extends AbstractSpecification<Equipment> {

    private final IP address;
    private final String name;
    private final int cidr;

    public NetworkAvailabilitySpec(Cobertura network){
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }

    @Override
    public boolean isSatisfiedBy(Equipment switchNetworks){
        return switchNetworks!=null && isNetworkAvailable(switchNetworks);
    }

    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("This network already exists");
    }

    private boolean isNetworkAvailable(Equipment switchNetworks){
        var availability = true;
        for (Cobertura network : ((Plan)switchNetworks).getSwitchNetworks()) {
            if(network.getNetworkAddress().equals(address) &&
                    network.getNetworkName().equals(name) &&
                    network.getNetworkCidr() == cidr)
                availability = false;
            break;
        }
        return availability;
    }
}
