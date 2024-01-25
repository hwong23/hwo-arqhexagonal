package dev.mimutual.application.ports.output;

import java.util.List;

import dev.mimutual.domain.entity.Prima;

public interface RouterViewOutputPort {

    List<Prima> fetchRouters();
}
