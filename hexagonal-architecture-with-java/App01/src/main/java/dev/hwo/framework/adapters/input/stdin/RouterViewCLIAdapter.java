package dev.hwo.framework.adapters.input.stdin;

import dev.hwo.application.ports.input.RouterViewInputPort;
import dev.hwo.application.usecases.RouterViewUseCase;
import dev.hwo.domain.Router;
import dev.hwo.domain.RouterType;
import dev.hwo.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}