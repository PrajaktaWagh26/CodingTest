package commons;

import java.io.IOException;
import java.util.Properties;

import exceptions.FileDataReadWriteException;

public class ReadProperties {
	String propertiesFile;

	public ReadProperties(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public Properties readProperties() throws FileDataReadWriteException {
		Properties propertiesFileData = new Properties();
		try {
			propertiesFileData.load(getClass().getResourceAsStream(propertiesFile));
			return propertiesFileData;
		} catch (IOException ioException) {
			throw new FileDataReadWriteException(ioException, propertiesFile);
		}
	}

}
