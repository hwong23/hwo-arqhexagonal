package dev.hwo.application.ports.output;

import dev.hwo.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}