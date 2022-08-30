package il.co.fibi.comm.mqbridge.rest;

import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.util.GlobalTracer;

@RestController
@RequestMapping("/v1")
public class MqbridgeController {
	private static Logger logger = Logger.getLogger(MqbridgeController.class.getName());
	@Autowired
	private MqbridgeService service;
	@Autowired
	private MqbridgeRequest request;
	@Autowired
	private MqbridgeResponse response;

	@PutMapping(value = "{trxid}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> send(
			@PathVariable("trxid") final String trxid,
			@RequestBody() final String body) {
		logger.fine("Received PUT for transaction " + trxid + " body length " + body.length());
		GlobalTracer.get().activeSpan().setOperationName("send");
		return buildResponse(HttpStatus.CREATED, service.send(request.setup(trxid, body, null)));
	}

	@GetMapping(value = "{trxid}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> receive(
			@PathVariable("trxid") final String trxid,
			@RequestParam("key") String key) {
		logger.fine("Received GET for transaction " + trxid + " with key " + key);
		GlobalTracer.get().activeSpan().setOperationName("receive");
		return buildResponse(HttpStatus.OK, service.receive(request.setup(trxid, null, key)));
	}

	@PostMapping(value = "{trxid}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> sendReceive(
			@PathVariable("trxid") final String trxid,
			@RequestBody() final String body) {
		logger.info("Received POST for transaction " + trxid + " body length " + body.length());
		GlobalTracer.get().activeSpan().setOperationName("sendreceive");
		service.send(request.setup(trxid, body, null));
		return response.getStatus() == 0
				? buildResponse(HttpStatus.CREATED,
						service.receive(
								request.setup(trxid, null, new JSONObject(response.getResponse()).getString("key"))))
				: buildResponse(HttpStatus.CREATED, response);
	}

	private ResponseEntity<String> buildResponse(HttpStatus success, MqbridgeResponse response) {
		if (response.getStatus() == 0) {
			return ResponseEntity.status(success).body(response.getResponse());
		} else if (response.getStatus() == -1) {
			return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
		} else if (response.getStatus() == -2) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
