package mvc.view.manager.textui;

import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) {
		ManagerTextUI m = new ManagerTextUI();
		try {
			m.start();
		} catch (SQLException e) {
			System.err.println("DB problems have been encountered");
			e.printStackTrace();
		}
	}

}
