package dev.com.framework.adapters.output.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import dev.com.application.funcionalidad.salida.PrimaViewOutputFuncionalidad;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.RouterId;
import dev.com.domain.vo.RouterType;

public class RouterViewFileAdapter implements PrimaViewOutputFuncionalidad {

    private static RouterViewFileAdapter instance;

    @Override
    public List<Prima> fetchRelatedRouters() {
        return readFileAsString();
    }

    private static List<Prima> readFileAsString() {
        List<Prima> routers = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(RouterViewFileAdapter.class.getResource("/routers.txt").getPath()))) {
            stream.forEach(line ->{
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Prima router = new Prima(RouterType.valueOf(type),RouterId.withId(id));
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
