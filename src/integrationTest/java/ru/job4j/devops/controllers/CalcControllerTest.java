package ru.job4j.devops.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.models.dto.Result;
import ru.job4j.devops.models.dto.TwoArgs;

import static org.assertj.core.api.Assertions.assertThat;

class CalcControllerTest extends ContainersConfig {

    @Autowired
    private CalcController calcController;

    @Test
    void whenOnePlusOneThenTwo() {
        var input = new TwoArgs(1, 1);
        var expected = new Result(2);
        var output = calcController.summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    void whenNegativeNumber() {
        var input = new TwoArgs(-1, -1);
        var expected = new Result(-2);
        var output = calcController.summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    void whenZeroPlusZero() {
        var input = new TwoArgs(0, 3);
        var expected = new Result(3);
        var output = calcController.summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    void whenTwoTimesTwoThenFour() {
        var input = new TwoArgs(2, 2);
        var expected = new Result(4);
        var output = calcController.times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    void whenZeroTimesZero() {
        var input = new TwoArgs(0, 0);
        var expected = new Result(0);
        var output = calcController.times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    void whenTimesNegatives() {
        var input = new TwoArgs(-3, -3);
        var expected = new Result(9);
        var output = calcController.times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    void whenTimesAnyNegatives() {
        var input = new TwoArgs(-3, 1);
        var expected = new Result(-3);
        var output = calcController.times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }
}