package com.example.clinica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Logg implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(Logg.class);

    @Override
    public void afterPropertiesSet() {
        PrintStream originalOut = System.out;

        PrintStream customOut = new PrintStream(System.out) {
            @Override
            public void println(String x) {
                originalOut.println(x);

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String logMessage = String.format("[INFO] %s - %s", timestamp, x);
                originalOut.println(logMessage);

                logger.info(x);
            }
        };

        System.setOut(customOut);
    }
}
