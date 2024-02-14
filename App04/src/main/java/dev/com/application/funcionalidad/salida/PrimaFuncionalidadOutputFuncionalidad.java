package dev.com.application.funcionalidad.salida;

import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterId;

public interface PrimaFuncionalidadOutputFuncionalidad {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
