package dev.com.framework.adapters.output.h2;

import dev.com.application.funcionalidad.output.PrimaCoberturaOutputPort;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaId;
import dev.com.framework.adapters.output.h2.data.RouterData;
import dev.com.framework.adapters.output.h2.mappers.RouterH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class PrimaCoberturaH2Adapter implements PrimaCoberturaOutputPort {

    private static PrimaCoberturaH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    private PrimaCoberturaH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Prima fetchRouterById(PrimaId routerId) {
        var routerData = em.getReference(RouterData.class, routerId.getUUID());
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Prima router) {
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
