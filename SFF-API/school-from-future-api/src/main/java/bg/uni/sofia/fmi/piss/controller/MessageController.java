package bg.uni.sofia.fmi.piss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.MessagesDAO;
import bg.uni.sofia.fmi.piss.data.MessageData;
import bg.uni.sofia.fmi.piss.dto.Message;

@RestController
public class MessageController {

	@Autowired
	MessagesDAO messagesDAO;

	public MessageController() {
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST, produces = { "application/xml",
			"application/json" })
	public void addMessage(@RequestBody Message data) {
		messagesDAO.insertMessage(data.getFrom(), data.getTo(), data.getContent(), data.getDate());
	}

	@RequestMapping(value = "/messages", method = RequestMethod.POST, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<Message>> getMessages(@RequestBody MessageData data) {
		final List<Message> messages = messagesDAO.getMessages(data.getFrom(), data.getTo());
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<Message>>(messages, headers, HttpStatus.OK);
	}
}
