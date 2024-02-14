package dev.com.application.funcionalidad.salida;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaId;

public interface PrimaFuncionalidadOutputFuncionalidad {
    Prima fetchRouterById(PrimaId routerId);

    boolean persistRouter(Prima router);
}
