package dev.com.domain.service;

import java.util.ArrayList;
import java.util.List;

import dev.com.domain.entity.Event;
import dev.com.domain.vo.ParsePolicyType;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType){
        var parsedEvents = new ArrayList<Event>();
        unparsedEvents.forEach(event ->{
            parsedEvents.add(Event.parsedEvent(event, policyType));
        });
        return parsedEvents;
    }
}
