package dev.mimutual.application.ports.input;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.application.ports.output.PrimaViewOutputPort;
import dev.mimutual.application.usecases.PrimaViewUseCase;
import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.service.RouterSearch;

public class PrimaViewInputPort implements PrimaViewUseCase {

    private PrimaViewOutputPort primaListOutputPort;

    public PrimaViewInputPort (PrimaViewOutputPort primaViewOutputPort) {
        this.primaListOutputPort = primaViewOutputPort;
    }

    @Override
    public List<Prima> getPrima(Predicate<Prima> filter) {
        var routers = primaListOutputPort.fetchPrimas();
        return RouterSearch.retrieveRouter(routers, filter);
    }
}
