package dev.com.framework.adapters.input.stdin;

import java.util.List;

import dev.com.application.ports.input.RouterViewInputPort;
import dev.com.application.usecases.RouterViewUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterType;
import dev.com.framework.adapters.output.file.RouterViewFileAdapter;

public class RouterViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Prima> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Prima.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}