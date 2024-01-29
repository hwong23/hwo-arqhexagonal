package dev.com.framework.adapters.output.file;

import java.util.ArrayList;
import java.util.List;

import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.domain.entity.Prima;
import dev.com.domain.entity.Plan;
import dev.com.domain.vo.*;

public class RouterNetworkFileAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileAdapter instance;

    private List<Prima> routers = new ArrayList<>();

    @Override
    public Prima fetchRouterById(PrimaId routerId) {
        Prima retrievedRouter = null;
        for(Prima router: routers){
            if(router.getRouterId().getId().equals(routerId.getId())){
                retrievedRouter = router;
                break;
            }
        }
        return retrievedRouter;
    }

    private void createSampleRouter() {
        var routerId = PrimaId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Cobertura(new IP("10.0.0.0"), "HR", 8);
        var networkSwitch = new Plan(SwitchType.LAYER3, SwitchId.withoutId(), List.of(network), new IP("9.0.0.9"));
        var router = new Prima(PrimaType.EDGE, routerId, networkSwitch);
        routers.add(router);
    }

    @Override
    public boolean persistRouter(Prima router){
        return this.routers.add(router);
    }

    private RouterNetworkFileAdapter(){
        createSampleRouter();
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }
}
