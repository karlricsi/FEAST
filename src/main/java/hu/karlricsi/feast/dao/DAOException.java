package hu.karlricsi.feast.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = 8913354330272562085L;

	public DAOException() {
	}

	public DAOException(String string) {
		super(string);
	}

	public DAOException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public DAOException(Throwable thrwbl) {
		super(thrwbl);
	}

}
