package dev.mimutual.application.ports.input;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.application.ports.output.RouterViewOutputPort;
import dev.mimutual.application.usecases.RouterViewUseCase;
import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.service.RouterSearch;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Prima> getRouters(Predicate<Prima> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return RouterSearch.retrieveRouter(routers, filter);
    }
}
