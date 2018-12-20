package bg.uni.sofia.fmi.piss.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example implements Serializable {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> sth() {
		return new ResponseEntity<String>("hi", HttpStatus.OK);
	}
}
