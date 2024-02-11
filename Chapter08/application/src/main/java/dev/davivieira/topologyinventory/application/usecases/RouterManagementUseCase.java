package dev.davivieira.topologyinventory.application.usecases;

import dev.davivieira.topologyinventory.domain.entity.CorePrima;
import dev.davivieira.topologyinventory.domain.entity.Prima;
import dev.davivieira.topologyinventory.domain.vo.*;

public interface RouterManagementUseCase {

    Prima createRouter(
            Id id,
            Vendor vendor,
            Model model,
            IP ip,
            Location location,
            PrimaType routerType);

    Prima removeRouter(Id id);

    Prima retrieveRouter(Id id);

    Prima persistRouter(Prima router);

    Prima addRouterToCoreRouter(
            Prima router, CorePrima coreRouter);

    Prima removeRouterFromCoreRouter(
            Prima router, CorePrima coreRouter);
}
