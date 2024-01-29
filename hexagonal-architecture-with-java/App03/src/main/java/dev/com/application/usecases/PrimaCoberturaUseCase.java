package dev.com.application.usecases;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.Cobertura;
import dev.com.domain.vo.PrimaId;

public interface PrimaCoberturaUseCase {

    Prima addCoberturaToPrima(PrimaId routerId, Cobertura network);
}
