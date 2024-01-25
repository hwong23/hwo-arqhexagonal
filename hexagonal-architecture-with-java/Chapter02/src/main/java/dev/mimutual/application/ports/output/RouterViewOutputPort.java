package dev.mimutual.application.ports.output;

import java.util.List;

import dev.mimutual.domain.entity.Router;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
