package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Base class for request
 */
@Data
@AllArgsConstructor
public class TwoArgs {
    private double first;
    private double second;

    /**
     * Constructor
     */
    public TwoArgs() {
    }
}
