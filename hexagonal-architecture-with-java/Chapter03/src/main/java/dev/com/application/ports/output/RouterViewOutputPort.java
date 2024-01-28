package dev.com.application.ports.output;

import java.util.List;

import dev.com.domain.entity.Router;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
