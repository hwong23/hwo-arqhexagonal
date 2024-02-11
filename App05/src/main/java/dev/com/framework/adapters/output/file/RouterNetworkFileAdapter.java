package dev.com.framework.adapters.output.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.com.application.funcionalidad.output.PrimaCoberturaOutputPort;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaId;
import dev.com.framework.adapters.output.file.json.RouterJson;
import dev.com.framework.adapters.output.file.mappers.RouterJsonFileMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class RouterNetworkFileAdapter implements PrimaCoberturaOutputPort {

    private static RouterNetworkFileAdapter instance;
    private List<RouterJson> routers;
    private InputStream resource;
    private ObjectMapper objectMapper;

    @Override
    public Prima fetchRouterById(PrimaId routerId) {
        var router = new Prima();
        for(RouterJson routerJson: routers){
            if(routerJson.getRouterId().equals(routerId.getUUID())){
                router = RouterJsonFileMapper.toDomain(routerJson);
                break;
            }
        }
        return router;
    }

    @Override
    public boolean persistRouter(Prima router) {
        var routerJson = RouterJsonFileMapper.toJson(router);
        try {
            String localDir = Paths.get("").toAbsolutePath().toString();
            File file = new File(localDir + "/inventory.json");
            file.delete();
            objectMapper.writeValue(file, routerJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void readJsonFile(){
        try {
            this.routers = objectMapper.
                    readValue(
                            resource,
                            new TypeReference<List<RouterJson>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RouterNetworkFileAdapter() {
        this.objectMapper = new ObjectMapper();
        this.resource = getClass().
                getClassLoader().
                getResourceAsStream("inventory.json");
        readJsonFile();
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }
}
