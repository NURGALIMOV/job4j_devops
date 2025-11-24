package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalcService {

    private final CalcEventRepository calcEventRepository;

    public void add(User user, int first, int second) {
        int result = first + second;
        CalcEvent calcEvent = new CalcEvent();
        calcEvent.setUserId(user.getId());
        calcEvent.setFirst(first);
        calcEvent.setSecond(second);
        calcEvent.setResult(result);
        calcEvent.setCreateDate(LocalDateTime.now());
        calcEvent.setType("ADD");
        calcEventRepository.save(calcEvent);
    }
}

