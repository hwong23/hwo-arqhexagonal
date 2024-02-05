package dev.com.framework.adapters.output.h2;

import dev.com.application.ports.output.RouterNetworkOutputPort;
import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterId;
import dev.com.framework.adapters.output.h2.data.RouterData;
import dev.com.framework.adapters.output.h2.mappers.RouterH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class PrimaCoberturaH2Adapter implements RouterNetworkOutputPort {

    private static PrimaCoberturaH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    private PrimaCoberturaH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        var routerData = em.getReference(RouterData.class, routerId.getUUID());
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerData = RouterH2Mapper.toH2(router);
        em.persist(routerData);
        return true;
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.em = em;
    }

    public static PrimaCoberturaH2Adapter getInstance() {
        if (instance == null) {
            instance = new PrimaCoberturaH2Adapter();
        }
        return instance;
    }
}
