package dev.com.framework.adapters.input.stdin;

import java.util.List;

import dev.com.application.funcionalidad.input.PrimaViewInputPort;
import dev.com.application.usecases.PrimaViewUseCase;
import dev.com.application.usecases.PrimaViewUseCase.RelatedRoutersCommand;
import dev.com.domain.entity.Prima;
import dev.com.framework.adapters.output.file.RouterViewFileAdapter;

public class PrimaViewCLIAdapter {

    PrimaViewUseCase routerViewUseCase;

    public PrimaViewCLIAdapter(){
        setAdapters();
    }

    public List<Prima> obtainRelatedRouters(String type) {
        RelatedRoutersCommand relatedRoutersCommand = new RelatedRoutersCommand(type);
        return routerViewUseCase.getRelatedRouters(relatedRoutersCommand);
    }

    private void setAdapters(){
        this.routerViewUseCase = new PrimaViewInputPort(RouterViewFileAdapter.getInstance());
    }
}