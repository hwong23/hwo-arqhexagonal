package dev.com.framework.adapters.input.stdin;

import java.util.List;

import dev.com.application.ports.input.RouterViewInputPort;
import dev.com.application.usecases.RouterViewUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaType;
import dev.com.framework.adapters.output.file.RouterViewFileAdapter;

public class PrimaViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public PrimaViewCLIAdapter(){
        setAdapters();
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }


    public List<Prima> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Prima.filterRouterByType(PrimaType.valueOf(type)));
    }
}