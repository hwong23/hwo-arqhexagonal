package dev.com.application.usecases;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.RouterId;

public interface PrimaCoberturaUseCase {

    Prima addNetworkToRouter(RouterId routerId, Network network);
}
