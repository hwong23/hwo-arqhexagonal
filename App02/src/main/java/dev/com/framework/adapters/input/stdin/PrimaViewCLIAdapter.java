package dev.com.framework.adapters.input.stdin;

import java.util.List;

import dev.com.application.ports.input.PrimaViewInputPort;
import dev.com.application.usecases.PrimaViewUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaType;
import dev.com.framework.adapters.output.file.PrimaViewFileAdapter;

public class PrimaViewCLIAdapter {

    private PrimaViewUseCase primaViewUseCase;

    public PrimaViewCLIAdapter() {
        setAdapters();
    }

    private void setAdapters() {
        this.primaViewUseCase = new PrimaViewInputPort(PrimaViewFileAdapter.getInstance());
    }


    public List<Prima> obtainRelatedPrimas(String type) {
        return primaViewUseCase.getPrima(
                Prima.filterPrimaByType(PrimaType.valueOf(type)));
    }
}