package com.gvnc.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by EXT01D3678 on 27.9.2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        SampleDataCreator sampleDataCreator = context.getBean(SampleDataCreator.class);

        String dataFilesPath = "C:\\Users\\gvnc\\datafiles\\";
        int bulkInsertSize = 200;

        sampleDataCreator.processData( dataFilesPath, bulkInsertSize);
    }
}