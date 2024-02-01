package dev.com.domain.vo;

import java.util.UUID;

public class PrimaId {

    private final UUID id;

    private PrimaId(UUID id){
        this.id = id;
    }

    public static PrimaId withId(String id) {
        return new PrimaId(UUID.fromString(id));
    }

    public static PrimaId withoutId() {
        return new PrimaId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return "PrimaId{" +
                "id='" + id + '\'' +
                '}';
    }
}
