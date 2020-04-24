package exceptions;

import java.io.IOException;

import org.apache.log4j.Logger;

public class FileDataReadWriteException extends Exception {
	private static final Logger log = Logger.getLogger(FileDataReadWriteException.class.getName());

	public FileDataReadWriteException(IOException ioException, String propertiesFile) {
		log.error("Cannot read/write in the given file : " + propertiesFile, ioException);
	}

}
