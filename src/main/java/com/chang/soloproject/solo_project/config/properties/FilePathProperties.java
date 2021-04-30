package com.chang.soloproject.solo_project.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "file-path")
public class FilePathProperties {

    private String root;
    private Upload upload;
    private Log log;
}