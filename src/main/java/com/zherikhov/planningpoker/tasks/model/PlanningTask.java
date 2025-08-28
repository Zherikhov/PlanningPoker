package com.zherikhov.planningpoker.tasks.model;

import java.util.Objects;

public class PlanningTask {
    private final String id;
    private final String summary;
    private final String description;

    public PlanningTask(String id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanningTask)) return false;
        PlanningTask task = (PlanningTask) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
