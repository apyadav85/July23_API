package ets.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class ProjectConfig {
	private Properties prop;
	
	public Properties readConfig(String fileName) throws Exception {
		prop = new Properties();
		FileInputStream ip = new FileInputStream("src/test/java/ets/Resources/"+fileName+".properties");
		prop.load(ip);
		
		return prop;
	}
	
	public void writeConfig(String key, String value) throws Exception {
		prop = new Properties();
		FileOutputStream op = new FileOutputStream("src/test/java/ets/Resources/Token.properties");
		prop.setProperty(key, value);
		prop.store(op, null);
	}

}
