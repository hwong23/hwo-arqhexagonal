package dev.mimutual.framework.adapters.input.stdin;

import java.util.List;

import dev.mimutual.application.ports.input.PrimaViewInputPort;
import dev.mimutual.application.usecases.PrimaViewUseCase;
import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.RouterType;
import dev.mimutual.framework.adapters.output.file.PrimaViewFileAdapter;

public class PrimaViewCLIAdapter {

    private PrimaViewUseCase primaViewUseCase;

    public PrimaViewCLIAdapter() {
        setAdapters();
    }

    private void setAdapters() {
        this.primaViewUseCase = new PrimaViewInputPort(PrimaViewFileAdapter.getInstance());
    }


    public List<Prima> obtainRelatedPrimas(String type) {
        return primaViewUseCase.getRouters(
                Prima.filterPrimaByType(RouterType.valueOf(type)));
    }
}