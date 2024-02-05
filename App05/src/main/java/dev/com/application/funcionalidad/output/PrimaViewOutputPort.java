package dev.com.application.funcionalidad.output;

import java.util.List;

import dev.com.domain.entity.Router;

public interface PrimaViewOutputPort {

    List<Router> fetchRelatedRouters();
}
