package dev.com.application.usecases;

import java.util.List;
import java.util.function.Predicate;

import dev.com.domain.entity.Router;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);
}