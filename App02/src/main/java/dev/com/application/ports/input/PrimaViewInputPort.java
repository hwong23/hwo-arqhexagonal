package dev.com.application.ports.input;

import java.util.List;
import java.util.function.Predicate;

import dev.com.application.ports.output.PrimaViewOutputPort;
import dev.com.application.usecases.PrimaViewUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.PrimaSearch;

public class PrimaViewInputPort implements PrimaViewUseCase {

    private PrimaViewOutputPort primaListOutputPort;

    public PrimaViewInputPort (PrimaViewOutputPort primaViewOutputPort) {
        this.primaListOutputPort = primaViewOutputPort;
    }

    @Override
    public List<Prima> getPrima(Predicate<Prima> filter) {
        var primas = primaListOutputPort.fetchPrimas();
        return PrimaSearch.retrievePrima(primas, filter);
    }
}
