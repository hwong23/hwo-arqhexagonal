package dev.com.application.ports.input;

import java.util.List;
import java.util.function.Predicate;

import dev.com.application.ports.output.RouterViewOutputPort;
import dev.com.application.usecases.RouterViewUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.RouterSearch;

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
