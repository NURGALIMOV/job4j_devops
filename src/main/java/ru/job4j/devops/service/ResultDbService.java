package ru.job4j.devops.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.models.dao.Result;
import ru.job4j.devops.repository.ResultRepository;

@Service
@AllArgsConstructor
public class ResultDbService {
    private final ResultRepository resultRepository;

    public void save(Result result) {
        resultRepository.save(result);
    }
}