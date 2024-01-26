package dev.mimutual.framework.adapters.output.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import dev.mimutual.application.ports.output.PrimaViewOutputPort;
import dev.mimutual.domain.entity.Prima;
import dev.mimutual.domain.vo.PrimaId;
import dev.mimutual.domain.vo.PrimaType;

public class PrimaViewFileAdapter implements PrimaViewOutputPort {

    private static PrimaViewFileAdapter instance;

    @Override
    public List<Prima> fetchPrimas() {
        return readFileAsString();
    }

    private static List<Prima> readFileAsString() {
        List<Prima> routers = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(PrimaViewFileAdapter.class.getClassLoader().
                                getResourceAsStream("routers.txt")))).lines()) {
            stream.forEach(line ->{
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Prima router = new Prima(PrimaType.valueOf(type),PrimaId.withId(id));
                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routers;
    }

    private PrimaViewFileAdapter() {
    }

    public static PrimaViewFileAdapter getInstance() {
        if (instance == null) {
            instance = new PrimaViewFileAdapter();
        }
        return instance;
    }
}
