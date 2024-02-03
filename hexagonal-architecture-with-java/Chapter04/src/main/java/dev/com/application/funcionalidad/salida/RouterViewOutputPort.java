package dev.com.application.funcionalidad.salida;

import java.util.List;

import dev.com.domain.entity.Router;

public interface RouterViewOutputPort {

    List<Router> fetchRelatedRouters();
}
