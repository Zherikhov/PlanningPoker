package com.zherikhov.planningpoker.domain.task;

/**
 * Lifecycle states of a task during planning poker.
 */
public enum TaskStatus {
    NEW,
    ESTIMATING,
    REVEALED,
    FINALIZED
}
