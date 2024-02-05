package dev.com.application.ports.output;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaId;

public interface PrimaCoberturaOutputPort {
    
    Prima fetchRouterById(PrimaId routerId);

    boolean persistRouter(Prima router);
}
