package com.zherikhov.planningpoker.integration.impl;

import org.springframework.stereotype.Component;

/**
 * Placeholder client for integrating with Jira's REST API.
 * In a real implementation this class would perform OAuth2 authentication,
 * fetch tasks via JQL, and update story points.
 */
@Component
public class JiraClient {

    public void connect(String clientId, String clientSecret) {
        // TODO: implement OAuth2 flow
    }

    public void importTasks(String jql) {
        // TODO: call Jira REST API to import tasks
    }

    public void exportStoryPoints(String issueKey, int storyPoints) {
        // TODO: update issue with story points
    }
}
