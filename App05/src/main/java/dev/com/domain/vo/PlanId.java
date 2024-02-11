package dev.com.domain.vo;

import java.util.UUID;

public class PlanId {

    private final UUID id;

    private PlanId(UUID id){
        this.id = id;
    }

    public static PlanId withId(String id){
        return new PlanId(UUID.fromString(id));
    }

    public UUID getUUID() {
        return id;
    }

    @Override
    public String toString() {
        return "SwitchId{" +
                "id='" + id + '\'' +
                '}';
    }
}
