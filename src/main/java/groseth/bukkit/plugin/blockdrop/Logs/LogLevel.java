package Logs;

public enum LogLevel {
	Info(4), Error(3), Debug(2), Verbose(1);
	
	private int _level;
	
	LogLevel(int level) {
		_level = level;
	}
	
	public int getLevel() {
		return _level;
	}
}
