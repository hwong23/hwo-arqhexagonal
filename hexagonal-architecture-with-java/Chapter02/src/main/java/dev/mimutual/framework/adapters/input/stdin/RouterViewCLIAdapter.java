package dev.mimutual.framework.adapters.input.stdin;

import java.util.List;

import dev.mimutual.application.ports.input.RouterViewInputPort;
import dev.mimutual.application.usecases.RouterViewUseCase;
import dev.mimutual.domain.entity.Router;
import dev.mimutual.domain.vo.RouterType;
import dev.mimutual.framework.adapters.output.file.RouterViewFileAdapter;

public class RouterViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}