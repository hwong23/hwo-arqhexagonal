package dev.com.application.funcionalidad.input;

import java.util.List;

import dev.com.application.funcionalidad.output.PrimaViewOutputPort;
import dev.com.application.usecases.PrimaViewUseCase;
import dev.com.domain.entity.Router;
import dev.com.domain.service.RouterSearch;
import dev.com.domain.vo.RouterType;

public class PrimaViewInputPort implements PrimaViewUseCase {

    private PrimaViewOutputPort routerListOutputPort;

    public PrimaViewInputPort(PrimaViewOutputPort routerGraphOutputPort) {
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
