package dev.com.application.usecases;

import java.util.List;
import java.util.function.Predicate;

import dev.com.domain.entity.Prima;

public interface PrimaViewUseCase {

    List<Prima> getPrima(Predicate<Prima> filter);
}
