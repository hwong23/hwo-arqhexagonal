package dev.com.application.funcionalidad.output;

public interface NotifyEventOutputPort {

    void sendEvent(String Event);
    String getEvent();
}
