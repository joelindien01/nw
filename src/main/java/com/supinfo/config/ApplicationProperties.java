package com.supinfo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by axelito on 02/09/16.
 */
@Configuration
@ConfigurationProperties(prefix="applicationProperties")
public class ApplicationProperties {

    private String defaultNotebookName;
    private String userSpaceRoot;

    public String getDefaultNotebookName() {
        return defaultNotebookName;
    }

    public void setDefaultNotebookName(String defaultNotebookName) {
        this.defaultNotebookName = defaultNotebookName;
    }

    public String getUserSpaceRoot() {
        return userSpaceRoot;
    }

    public void setUserSpaceRoot(String userSpaceRoot) {
        this.userSpaceRoot = userSpaceRoot;
    }
}
