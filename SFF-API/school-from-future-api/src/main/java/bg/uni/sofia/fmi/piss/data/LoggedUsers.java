package bg.uni.sofia.fmi.piss.data;

import java.util.ArrayList;
import java.util.List;

public final class LoggedUsers {

	private static List<UserData> loggedUsers = new ArrayList<>();

	public static void insert(UserData u) {
		loggedUsers.add(u);
	}

	public static void remove(UserData u) {
		loggedUsers.remove(u);
	}

	public static List<UserData> getLoggedUsers() {
		return loggedUsers;
	}
}
