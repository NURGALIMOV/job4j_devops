package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Base class for result.
 */
@Data
@AllArgsConstructor
public class Result {
    private double value;

    /**
     * Constructor.
     */
    public Result() {
    }
}
