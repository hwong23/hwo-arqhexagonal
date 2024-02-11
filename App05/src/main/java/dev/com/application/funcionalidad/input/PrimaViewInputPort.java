package dev.com.application.funcionalidad.input;

import java.util.List;

import dev.com.application.funcionalidad.output.PrimaViewOutputPort;
import dev.com.application.usecases.PrimaViewUseCase;
import dev.com.domain.entity.Prima;
import dev.com.domain.service.PrimaSearch;
import dev.com.domain.vo.PrimaType;

public class PrimaViewInputPort implements PrimaViewUseCase {

    private PrimaViewOutputPort routerListOutputPort;

    public PrimaViewInputPort(PrimaViewOutputPort routerGraphOutputPort) {
        this.routerListOutputPort = routerGraphOutputPort;
    }

    @Override
    public List<Prima> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand) {
        var type = relatedRoutersCommand.getType();
        var routers = routerListOutputPort.fetchRelatedRouters();
        return fetchRelatedEdgeRouters(type, routers);
    }

    private List<Prima> fetchRelatedEdgeRouters(PrimaType type, List<Prima> routers){
        return PrimaSearch.getRouters(type, routers);
    }
}
