package bg.uni.sofia.fmi.piss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.EventDAO;
import bg.uni.sofia.fmi.piss.dao.UserDAO;
import bg.uni.sofia.fmi.piss.data.LoggedUsers;
import bg.uni.sofia.fmi.piss.data.LoginData;
import bg.uni.sofia.fmi.piss.data.RegisterData;
import bg.uni.sofia.fmi.piss.data.UserData;
import bg.uni.sofia.fmi.piss.dto.Event;
import bg.uni.sofia.fmi.piss.dto.User;

@RestController
public class UserController {
	@Autowired
	UserDAO userDAO;

	@Autowired
	EventDAO eventDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/xml", "application/json" })
	public ResponseEntity<User> login(@RequestBody LoginData data) {
		final User user = userDAO.findUser(data.getEmail(), data.getPass());
		if (user != null) {
			LoggedUsers
					.insert(new UserData(user.getFirstName().concat(" ").concat(user.getLastName()), user.getEmail()));
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void login(@RequestBody UserData user) {
		LoggedUsers.remove(user);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { "application/xml",
			"application/json" })
	public ResponseEntity register(@RequestBody RegisterData data) {
		userDAO.registerUser(data);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/active-users", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<UserData>> getLoggedUsers() {
		final List<UserData> users = LoggedUsers.getLoggedUsers();
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<UserData>>(users, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/events/{subject}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<Event>> getSubjectEvents(@PathVariable int subject) {
		final List<Event> events = eventDAO.getEvents(subject);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<Event>>(events, headers, HttpStatus.OK);
	}
}
