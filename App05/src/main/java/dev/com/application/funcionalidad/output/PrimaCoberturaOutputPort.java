package dev.com.application.funcionalidad.output;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterId;

public interface PrimaCoberturaOutputPort {
    Prima fetchRouterById(RouterId routerId);

    boolean persistRouter(Prima router);
}
