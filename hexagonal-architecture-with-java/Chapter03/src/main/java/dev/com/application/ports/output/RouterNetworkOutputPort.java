package dev.com.application.ports.output;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterId;

public interface RouterNetworkOutputPort {
    Prima fetchRouterById(RouterId routerId);

    boolean persistRouter(Prima router);
}
