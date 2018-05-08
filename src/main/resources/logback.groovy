import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.*

def LOG_PATH = System.getProperty("log.dir") ?: "log"

def ACTIVE_PROFILE = System.getProperty("spring.profiles.active") ?: "default"

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}

appender("FILE", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy){
        fileNamePattern = "${LOG_PATH}/product-category.%d{yyyy-MM-dd}.log"
        maxHistory = 10
    }
}

def appenderList = ['STDOUT', 'FILE']

logger('org.springframework.security', WARN, appenderList, false)
logger('org.apache.http', TRACE, appenderList, false)
root(INFO, appenderList)
