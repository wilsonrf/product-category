package com.wilsonfranca.procuctcategory.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wilson.franca on 06/05/18.
 */
@Configuration
@ConfigurationProperties(prefix = "fixer")
public class FixerConfigurationProperties {

    private App app;
    private Urls urls;

    public String getBase() {
        return app.getBase();
    }

    public String getAccessKey() {
        return app.getAccessKey();
    }

    public String getBaseUrl() {
        return urls.getBase();
    }

    public String getLatestUrl() {
        return urls.getBase() + urls.getLatest();
    }


    public static class App {
        private String base;
        private String accessKey;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }
    }

    public static class Urls {
        private String base;
        private String latest;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getLatest() {
            return latest;
        }

        public void setLatest(String latest) {
            this.latest = latest;
        }

    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }
}
