package enums;

public class Connections_Status {
	
	public enum AC{
		// value-labels used in order to make the status returned by Connections method more secure/maintainable
		OK, INVALID_ARG, CONNECTION_EXIST
	}
	
	public enum RC{
		// value-labels used in order to make the status returned by Connections method more secure/maintainable
		OK, INVALID_ARG, CONNECTION_NOT_EXIST
	}
}
