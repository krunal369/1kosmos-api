package com.qa.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	public static Properties readPropertyFileValue() throws FileNotFoundException {
		/***
		 * This method is used to get variables at configuration level mentioned inside configuration.properties file.
		 * It will load whole file and return Properties reference.
		 */
        Properties prop = new Properties();
        
        try (InputStream inputStream = new FileInputStream("./configurations/configuration.properties")) {               
            prop.load(inputStream);
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
