package dev.com.domain.vo;

import java.util.UUID;

public class PrimaId {

    private final UUID id;

    private PrimaId(UUID id) {
        this.id = id;
    }

    public static PrimaId withId(String id) {
        return new PrimaId(UUID.fromString(id));
    }

    public static PrimaId withoutId() {
        return new PrimaId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}


