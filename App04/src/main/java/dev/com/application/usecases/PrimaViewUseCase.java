package dev.com.application.usecases;

import java.util.List;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaType;

public interface PrimaViewUseCase {

    List<Prima> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand);

    class RelatedRoutersCommand {

        private PrimaType type;

        public RelatedRoutersCommand(String type){
            this.type = PrimaType.valueOf(type);
        }

        public PrimaType getType() {
            return type;
        }
    }
}
