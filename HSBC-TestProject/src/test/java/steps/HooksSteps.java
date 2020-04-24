package steps;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;

import commons.ReadProperties;
import exceptions.FileDataReadWriteException;

public class HooksSteps {
	@BeforeClass
	public void setUpLogFile() throws FileDataReadWriteException {
		ReadProperties readPropertiesFile = new ReadProperties("/log4j.properties");
		PropertyConfigurator.configure(readPropertiesFile.readProperties());
	}
}
