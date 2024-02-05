package dev.com.application.funcionalidad.output;

import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterId;

public interface PrimaCoberturaOutputPort {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
