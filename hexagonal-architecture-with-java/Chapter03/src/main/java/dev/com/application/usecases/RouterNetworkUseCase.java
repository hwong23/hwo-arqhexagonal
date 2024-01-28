package dev.com.application.usecases;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.Network;
import dev.com.domain.vo.PrimaId;

public interface RouterNetworkUseCase {

    Prima addNetworkToRouter(PrimaId routerId, Network network);
}
