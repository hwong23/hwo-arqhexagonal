package dev.davivieira.topologyinventory.application.ports.output;


import dev.davivieira.topologyinventory.domain.entity.Plan;
import dev.davivieira.topologyinventory.domain.vo.Id;

public interface SwitchManagementOutputPort {
    Plan retrieveSwitch(Id id);
}
