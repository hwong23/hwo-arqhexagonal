package dev.com.application.usecases;

import java.util.List;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterType;

public interface RouterViewUseCase {

    List<Prima> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand);

    class RelatedRoutersCommand {

        private RouterType type;

        public RelatedRoutersCommand(String type){
            this.type = RouterType.valueOf(type);
        }

        public RouterType getType() {
            return type;
        }
    }
}
