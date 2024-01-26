package dev.mimutual.framework.adapters.input.stdin;

import java.util.List;

import dev.mimutual.application.ports.input.PrimaViewInputPort;
import dev.mimutual.application.usecases.PrimaViewUseCase;
import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.RouterType;
import dev.mimutual.framework.adapters.output.file.RouterViewFileAdapter;

public class PrimaViewCLIAdapter {

    private PrimaViewUseCase routerViewUseCase;

    public PrimaViewCLIAdapter() {
        setAdapters();
    }

    private void setAdapters() {
        this.routerViewUseCase = new PrimaViewInputPort(RouterViewFileAdapter.getInstance());
    }


    public List<Prima> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Prima.filterRouterByType(RouterType.valueOf(type)));
    }
}