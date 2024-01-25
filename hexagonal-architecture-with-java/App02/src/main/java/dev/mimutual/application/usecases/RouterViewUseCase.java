package dev.mimutual.application.usecases;

import java.util.List;
import java.util.function.Predicate;

import dev.mimutual.domain.entity.Router;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);
}
