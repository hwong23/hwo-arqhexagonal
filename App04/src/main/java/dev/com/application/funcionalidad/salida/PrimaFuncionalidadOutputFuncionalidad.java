package dev.com.application.funcionalidad.salida;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterId;

public interface PrimaFuncionalidadOutputFuncionalidad {
    Prima fetchRouterById(RouterId routerId);

    boolean persistRouter(Prima router);
}
