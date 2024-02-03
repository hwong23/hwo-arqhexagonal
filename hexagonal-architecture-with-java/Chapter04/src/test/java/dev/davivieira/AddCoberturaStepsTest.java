package dev.davivieira;

import dev.com.domain.entity.Router;
import dev.com.domain.specification.CIDRSpecification;
import dev.com.domain.specification.NetworkAvailabilitySpecification;
import dev.com.domain.vo.IP;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;
import dev.com.framework.adapters.output.file.PrimaCoberturaFileAdapter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddCoberturaStepsTest {

    private RouterId routerId;
    private Router router;
    private final PrimaCoberturaFileAdapter routerNetworkFileAdapter =  
        PrimaCoberturaFileAdapter.getInstance();
    private final Network network = new Network(new IP("20.0.0.0"), "Marketing", 8);

    @Given("I provide a router ID and the network details")
    public void obtain_routerId() {
        this.routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    }

    @When("I found the router")
    public void lookup_router() {
        router = routerNetworkFileAdapter.fetchRouterById(routerId);
    }

    @And("The network address is valid and doesn't already exists")
    public void check_address_validity_and_existence() {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");
    }

    @Given("The CIDR is valid")
    public void check_cidr() {
        var cidrSpec = new CIDRSpecification();
        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);
    }

    @Then("Add the network to the router")
    public void add_network() {
        router.addNetworkToSwitch(network);
    }
}
