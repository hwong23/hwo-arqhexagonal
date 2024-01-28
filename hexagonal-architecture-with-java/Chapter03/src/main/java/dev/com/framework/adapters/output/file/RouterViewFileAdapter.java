package dev.com.framework.adapters.output.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import dev.com.application.ports.output.RouterViewOutputPort;
import dev.com.domain.entity.Prima;
import dev.com.domain.vo.PrimaId;
import dev.com.domain.vo.PrimaType;

public class RouterViewFileAdapter implements RouterViewOutputPort {

    private static RouterViewFileAdapter instance;

    @Override
    public List<Prima> fetchRouters() {
        return readFileAsString();
    }

    private static List<Prima> readFileAsString() {
        List<Prima> routers = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(RouterViewFileAdapter.class.getClassLoader().
                                getResourceAsStream("routers.txt")))).lines()) {
            stream.forEach(line ->{
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Prima router = new Prima(PrimaType.valueOf(type),PrimaId.withId(id));
                routers.add(router);
            });
        } catch (Exception e){
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
