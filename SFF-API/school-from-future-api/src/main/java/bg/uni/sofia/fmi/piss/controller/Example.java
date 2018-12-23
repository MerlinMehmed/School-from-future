package bg.uni.sofia.fmi.piss.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.RoleDAO;

@RestController
public class Example implements Serializable {

	@Autowired
	RoleDAO roleDao;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> sth() {
		return new ResponseEntity<String>("hi", HttpStatus.OK);
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public ResponseEntity<String> getRoles() {
		roleDao.getRoles();
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
}
