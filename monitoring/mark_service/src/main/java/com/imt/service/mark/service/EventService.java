package com.imt.service.mark.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imt.service.mark.dto.EventDto;
import com.imt.service.mark.entity.EventEntity;
import com.imt.service.mark.entity.MarkEntity;
import com.imt.service.mark.exception.NoEventException;
import com.imt.service.mark.mapper.EventMapper;
import com.imt.service.mark.repository.EventRepository;
import com.imt.service.mark.repository.MarkRepository;

@Service
public class EventService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MarkRepository markRepository;

    private Map<Double, List<EventEntity>> computeEventEvaluation () {
        return eventRepository.findAll().stream()
                   .reduce(
                       new TreeMap<Double, List<EventEntity>>(),
                       (acc, event) -> {
                           int markSum = event.getMarks().stream().mapToInt(MarkEntity::getMarkValue)
                               .sum();
                           double markRate = (double) markSum / (double) event.getMarks().size();
                           if (!acc.containsKey(markRate)) acc.put(markRate, new ArrayList());
                           acc.get(markRate).add(event);
                           return acc;
                       },
                       (acc, newAcc) -> {
                           acc.putAll(newAcc);
                           return acc;
                       });
    }

    public EventDto findTopEventByMarks () {
        /* V1 */
        /*
        Map<Double, List<EventEntity>> markRates = this.computeEventEvaluation();
        if (markRates.isEmpty()) throw new NoEventException();
        List<Double> topMark = markRates.keySet().stream()
                .sorted(Collections.reverseOrder())
                .limit(10)
                .toList();
        if (topMark.isEmpty()) throw new NoEventException();
        EventEntity topEvent = markRates.get(topMark.get(0)).get(0);
        return EventMapper.mapToDto(topEvent);
        */

        /* V2 */
        Page<UUID> topEventPage = markRepository.findEventIdOrderByEvaluation(PageRequest.of(0, 1));
        if (topEventPage.isEmpty()) throw new NoEventException();
        EventEntity topEvent = topEventPage.stream().findFirst()
            .map((id) -> eventRepository.findById(id).get())
            .orElseThrow(() -> new NoEventException());
        topEvent.setMarks(null);
        return EventMapper.mapToDto(topEvent);
    }
}
