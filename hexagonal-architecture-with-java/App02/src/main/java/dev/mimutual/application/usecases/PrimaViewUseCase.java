package dev.mimutual.application.usecases;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.domain.entity.Prima;

public interface PrimaViewUseCase {

    List<Prima> getPrima(Predicate<Prima> filter);
}
