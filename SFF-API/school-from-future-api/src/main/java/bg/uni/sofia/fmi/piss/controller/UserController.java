package bg.uni.sofia.fmi.piss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.UserDAO;
import bg.uni.sofia.fmi.piss.data.LoginData;
import bg.uni.sofia.fmi.piss.dto.User;

@RestController
public class UserController {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/xml", "application/json" })
	public ResponseEntity<User> login(@RequestBody LoginData data) {
		final User user = userDAO.findUser(data.getEmail(), data.getPass());
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

}