package dev.com.framework.adapters.output.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import dev.com.application.ports.output.RouterViewOutputPort;
import dev.com.domain.entity.Router;
import dev.com.domain.vo.RouterId;
import dev.com.domain.vo.RouterType;

public class RouterViewFileAdapter implements RouterViewOutputPort {

    private static RouterViewFileAdapter instance;

    @Override
    public List<Router> fetchRelatedRouters() {
        return readFileAsString();
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(RouterViewFileAdapter.class.getResource("/routers.txt").getPath()))) {
            stream.forEach(line ->{
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Router router = new Router(RouterType.valueOf(type),RouterId.withId(id));
                routers.add(router);
            });
        } catch (IOException e){
           e.printStackTrace();
        }
        return routers;
    }


    private RouterViewFileAdapter() {
    }

    public static RouterViewFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterViewFileAdapter();
        }
        return instance;
    }
}
