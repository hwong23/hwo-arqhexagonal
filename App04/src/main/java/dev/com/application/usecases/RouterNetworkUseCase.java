package dev.com.application.usecases;

import dev.com.domain.entity.Router;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);
}
