package dev.com.domain.service;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterType;

public class RouterSearch {

    public static List<Router> getRouters(RouterType type, List<Router> routers) {
        var routersList = new ArrayList<Router>();
        routers.forEach(router -> {
            if(router.isType(type)){
                routersList.add(router);
            }
        });
        return routersList;
    }
}
