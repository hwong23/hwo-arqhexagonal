package dev.hwo.application.usecases;

import java.util.List;
import java.util.function.Predicate;

import dev.hwo.domain.Prima;

public interface ValidarValorCuotaUseCase {
    boolean validaValorCuotaPrima (Predicate<Prima> prima);
}
