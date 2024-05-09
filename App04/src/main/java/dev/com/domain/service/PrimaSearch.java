package dev.com.domain.service;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaType;

public class PrimaSearch {

    public static List<Prima> getRouters(PrimaType type, List<Prima> routers) {
        var routersList = new ArrayList<Prima>();
        routers.forEach(router -> {
            if(router.isType(type)){
                routersList.add(router);
            }
        });
        return routersList;
    }
}
