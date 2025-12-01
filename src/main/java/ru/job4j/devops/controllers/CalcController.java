package ru.job4j.devops.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.devops.models.dto.Result;
import ru.job4j.devops.models.dto.TwoArgs;
import ru.job4j.devops.service.ResultDbService;

import java.time.LocalDate;

/**
 * Base controller
 */
@RestController
@RequestMapping("calc")
@RequiredArgsConstructor
public class CalcController {

    private final ResultDbService resultService;

    /**
     * Summarizes two numbers
     *
     * @param twoArgs - request
     * @return summarise result
     */
    @PostMapping("summarise")
    public ResponseEntity<Result> summarise(@RequestBody TwoArgs twoArgs) {
        var result = new ru.job4j.devops.models.dao.Result();
        result.setFirstArg(twoArgs.getFirst());
        result.setSecondArg(twoArgs.getSecond());
        result.setResult(twoArgs.getFirst() + twoArgs.getSecond());
        result.setOperation("+");
        result.setCreateDate(LocalDate.now());
        resultService.save(result);
        return ResponseEntity.ok(new Result(result.getResult()));
    }

    /**
     * Multiples two numbers
     *
     * @param twoArgs - request
     * @return Multiples result
     */
    @PostMapping("times")
    public ResponseEntity<Result> times(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() * twoArgs.getSecond();
        return ResponseEntity.ok(new Result(result));
    }
}
