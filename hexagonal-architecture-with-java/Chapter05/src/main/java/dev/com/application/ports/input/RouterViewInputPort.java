package dev.com.application.ports.input;

import java.util.List;

import dev.com.application.ports.output.RouterViewOutputPort;
import dev.com.application.usecases.RouterViewUseCase;
import dev.com.domain.entity.Router;
import dev.com.domain.service.RouterSearch;
import dev.com.domain.vo.RouterType;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerGraphOutputPort) {
        this.routerListOutputPort = routerGraphOutputPort;
    }

    @Override
    public List<Router> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand) {
        var type = relatedRoutersCommand.getType();
        var routers = routerListOutputPort.fetchRelatedRouters();
        return fetchRelatedEdgeRouters(type, routers);
    }

    private List<Router> fetchRelatedEdgeRouters(RouterType type, List<Router> routers){
        return RouterSearch.getRouters(type, routers);
    }
}
