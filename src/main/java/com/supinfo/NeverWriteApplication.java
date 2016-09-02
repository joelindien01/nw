package com.supinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class NeverWriteApplication {

    public static final String ROOT = "";

    public static void main(String[] args) {
		SpringApplication.run(NeverWriteApplication.class, args);
	}

    public static String getProjectFolder() {
        URI uri = null;
        try {
            uri = new ClassPathResource("application.yaml").getURI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fullPathToSubfolder = new File(uri).getAbsoluteFile();
        return fullPathToSubfolder.getAbsolutePath().split("target")[0];
    }
}
