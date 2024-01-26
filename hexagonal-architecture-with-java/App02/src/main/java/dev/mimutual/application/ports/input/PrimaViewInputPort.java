package dev.mimutual.application.ports.input;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.application.ports.output.RouterViewOutputPort;
import dev.mimutual.application.usecases.PrimaViewUseCase;
import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.service.RouterSearch;

public class PrimaViewInputPort implements PrimaViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    public PrimaViewInputPort (RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Prima> getRouters(Predicate<Prima> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return RouterSearch.retrieveRouter(routers, filter);
    }
}
