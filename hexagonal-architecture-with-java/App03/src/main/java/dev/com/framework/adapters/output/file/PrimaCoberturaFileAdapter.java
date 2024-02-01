package dev.com.framework.adapters.output.file;

import java.util.ArrayList;
import java.util.List;

import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.domain.entity.Prima;
import dev.com.domain.entity.Plan;
import dev.com.domain.vo.*;

public class PrimaCoberturaFileAdapter implements RouterNetworkOutputPort {

    private static PrimaCoberturaFileAdapter instance;

    private List<Prima> primas = new ArrayList<>();

    @Override
    public Prima fetchRouterById(PrimaId routerId) {
        Prima retrievedRouter = null;
        for(Prima router: primas){
            if ( router.getRouterId().getId().equals(routerId.getId()) ) {
                retrievedRouter = router;
                break;
            }
        }
        return retrievedRouter;
    }

    private void createSampleRouter() {
        var primaId = PrimaId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var cobertura = new Cobertura(new IP("10.0.0.0"), "HR", 8);
        var networkSwitch = new Plan(
            SwitchType.LAYER3, 
            SwitchId.withoutId(), 
            List.of(cobertura), 
            new IP("9.0.0.9"));
        var prima = new Prima(PrimaType.EDGE, primaId, networkSwitch);
        primas.add(prima);
    }

    @Override
    public boolean persistRouter(Prima prima){
        return this.primas.add(prima);
    }

    private PrimaCoberturaFileAdapter(){
        createSampleRouter();
    }

    public static PrimaCoberturaFileAdapter getInstance() {
        if (instance == null) {
            instance = new PrimaCoberturaFileAdapter();
        }

        return instance;
    }
}
