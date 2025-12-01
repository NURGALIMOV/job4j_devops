package ru.job4j.devops.models.dto;

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
