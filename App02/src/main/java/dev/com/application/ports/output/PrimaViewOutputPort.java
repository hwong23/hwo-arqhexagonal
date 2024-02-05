package dev.com.application.ports.output;

import java.util.List;

import dev.com.domain.entity.Prima;

public interface PrimaViewOutputPort {

    List<Prima> fetchPrimas();
}
