package il.co.fibi.comm.mqbridge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import il.co.fibi.comm.mqbridge.cache.ProtoParams;
import il.co.fibi.comm.mqbridge.service.AbstractMqbridgeService;
import il.co.fibi.comm.mqbridge.service.ProducedService;

@RestController
public class MqbridgeService {
	@Autowired
	ProtoParams params;
	@Autowired
	@ProducedService
	AbstractMqbridgeService service;
	@Autowired
	MqbridgeResponse response;

	public MqbridgeResponse send(MqbridgeRequest request) {
		return response.fromServiceResponse(request.getTrxid(),
				service.init(params.build(request.getTrxid())).send(request.toServiceRequest()));
	}

	public MqbridgeResponse receive(MqbridgeRequest request) {
		return response.fromServiceResponse(request.getTrxid(),
				service.init(params.build(request.getTrxid())).receive(request.toServiceRequest()));
	}
}
