package dev.davivieira.topologyinventory.application.ports.output;

import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.vo.Id;

public interface RouterManagementOutputPort {
    Prima retrieveRouter(Id id);

    Prima removeRouter(Id id);

    Prima persistRouter(Prima router);
}
