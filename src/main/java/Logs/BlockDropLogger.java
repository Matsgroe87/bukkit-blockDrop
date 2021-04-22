package Logs;

import java.util.logging.Logger;

public class BlockDropLogger {
	private Logger _log;

	public BlockDropLogger(Logger log) {
		_log = log;
	}
	
	public void tryLog(String message) {
		tryLog(message, LogLevel.Debug);
	}
	
	public void tryLog(String message, LogLevel logLevel) {
		if (_log != null && message != null) {
			_log.info(message);
		}
	}
}
