package dev.com.domain.policy;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import dev.com.domain.entity.Event;

public sealed interface EventParser permits RegexEventParser, SplitEventParser {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("UTC"));

    Event parseEvent(String event);
}
