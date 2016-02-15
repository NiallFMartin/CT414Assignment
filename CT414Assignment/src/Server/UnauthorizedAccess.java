package Server;

public class UnauthorizedAccess extends Exception {

	public UnauthorizedAccess(String reason) {
		super(reason);
	}
}

