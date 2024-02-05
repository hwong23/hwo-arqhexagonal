package dev.com.framework.adapters.input.stdin;

import java.util.List;

import dev.com.application.ports.input.RouterViewInputPort;
import dev.com.application.usecases.RouterViewUseCase;
import dev.com.application.usecases.RouterViewUseCase.RelatedRoutersCommand;
import dev.com.domain.entity.Router;
import dev.com.framework.adapters.output.file.RouterViewFileAdapter;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        RelatedRoutersCommand relatedRoutersCommand = new RelatedRoutersCommand(type);
        return routerViewUseCase.getRelatedRouters(relatedRoutersCommand);
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}