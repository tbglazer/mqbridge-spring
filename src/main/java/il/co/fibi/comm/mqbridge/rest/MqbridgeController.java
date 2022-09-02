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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.util.GlobalTracer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("mqbridge/v1")
public class MqbridgeController {
	private static Logger logger = Logger.getLogger(MqbridgeController.class.getName());
	@Autowired
	private MqbridgeService service;
	@Autowired
	private MqbridgeRequest request;
	@Autowired
	private MqbridgeResponse response;

	@PutMapping(value = "{trxid}", consumes = "application/json", produces = "application/json")
	@ApiOperation(code = 200, value = "Send a transmision")
	public ResponseEntity<String> send(
			@RequestHeader(name = "remote_user", required = true) final String user,
			@PathVariable(name = "trxid", required = true) final String trxid, 
			@RequestBody(required = true) final String body) {
		logger.fine("Received PUT for transaction " + trxid + " body length " + body.length());
		GlobalTracer.get().activeSpan().setOperationName("send");
		return buildResponse(HttpStatus.CREATED, service.send(request.setup(trxid, body, null)));
	}

	@GetMapping(value = "{trxid}", consumes = "application/json", produces = "application/json")
	@ApiOperation(code = 200, value = "Receive a transmision by message id")
	public ResponseEntity<String> receive(
			@RequestHeader(name = "remote_user", required = true) final String user,
			@PathVariable(name = "trxid", required = true) final String trxid, 
			@RequestParam(name = "key", required = true) String key) {
		logger.fine("Received GET for transaction " + trxid + " with key " + key);
		GlobalTracer.get().activeSpan().setOperationName("receive");
		return buildResponse(HttpStatus.OK, service.receive(request.setup(trxid, null, key)));
	}

	@PostMapping(value = "{trxid}", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Send and receive a transmision")
	@ApiResponse(code = 201, message = "The transmission was successful")
	public ResponseEntity<String> sendReceive(
			@RequestHeader(name = "remote_user", required = true) final String user,
			@PathVariable(name = "trxid", required = true) @ApiParam(name = "trxid", value = "transaction id", example = "N612", required = true) final String trxid,
			@RequestBody(required = true) @ApiParam(example = "{\"GKSI_HdrBtt\":{\"GL_TRANS_ID\":\"N612\",\"GL_SHAA\":\"130100\",\"GL_TR_NOCHECHI\":\"26062020\",\"GL_TR_ASAKIM\":\"26062020\",\"GL_TR_MARECHET\":\"26062020\",\"SVIVA\":\"T\",\"GL_MISHMERET\":\"0\",\"GL_GIRSA\":\"3400\",\"GL_PEULA\":\"S612\",\"GL_BANK\":\"31\",\"GL_SNIF\":\"284\",\"GL_CH\":\"100633\",\"GL_MAKOR_PU\":\"UE\",\"GL_MAKOR_TAHANA\":\"8\",\"GL_BANK_MARECHET\":\"31\",\"GL_SNIF_MARECHET\":\"284\",\"GL_SNIF_PAKID\":\"284\",\"GL_ZIHUI_PAKID\":\"180025\",\"GL_SAMCHUT_PAKID\":\"5\",\"GL_SNIF_PATUACH\":\"O\",\"SAMCHUT_NIDRESHET\":\"5\",\"GL_GIRSA_BTT\":\"061501\",\"GL_NOSE_ISKI\":\"T\"}}", required = true) final String body) {
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
