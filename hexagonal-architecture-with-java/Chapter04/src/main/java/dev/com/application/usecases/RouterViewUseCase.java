package dev.com.application.usecases;

import java.util.List;

import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterType;

public interface RouterViewUseCase {

    List<Router> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand);

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
