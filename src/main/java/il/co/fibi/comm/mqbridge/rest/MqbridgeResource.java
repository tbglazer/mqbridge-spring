package il.co.fibi.comm.mqbridge.rest;

import java.util.logging.Logger;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.json.JSONObject;

import io.opentracing.util.GlobalTracer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@RequestScoped
@Path("v1")
public class MqbridgeResource {
	private static Logger logger = Logger.getLogger(MqbridgeResource.class.getName());
	@Inject	MqbridgeController controller;
	@Inject MqbridgeRequest request;
	@Inject MqbridgeResponse response;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{trxid}")
	public Response send(
			@Parameter(description = "The CICS transaction id", required = true, example = "N612", schema = @Schema(type = SchemaType.STRING)) @PathParam("trxid") @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]{3}") final String trxid,
			@RequestBody(description = "The JSON request body", required = true, content = @Content(mediaType = "application/json", example = "{\"GKSI_HdrBtt\":{\"GL_TRANS_ID\":\"N612\",\"GL_SHAA\":\"130100\",\"GL_TR_NOCHECHI\":\"26062020\",\"GL_TR_ASAKIM\":\"26062020\",\"GL_TR_MARECHET\":\"26062020\",\"SVIVA\":\"T\",\"GL_MISHMERET\":\"0\",\"GL_GIRSA\":\"3400\",\"GL_PEULA\":\"S612\",\"GL_BANK\":\"31\",\"GL_SNIF\":\"284\",\"GL_CH\":\"100633\",\"GL_MAKOR_PU\":\"UE\",\"GL_MAKOR_TAHANA\":\"8\",\"GL_BANK_MARECHET\":\"31\",\"GL_SNIF_MARECHET\":\"284\",\"GL_SNIF_PAKID\":\"284\",\"GL_ZIHUI_PAKID\":\"180025\",\"GL_SAMCHUT_PAKID\":\"5\",\"GL_SNIF_PATUACH\":\"O\",\"SAMCHUT_NIDRESHET\":\"5\",\"GL_GIRSA_BTT\":\"061501\",\"GL_NOSE_ISKI\":\"T\"}}")) final String body) {
		logger.fine("Received PUT for transaction " + trxid + " body length " + body.length());
		GlobalTracer.get().activeSpan().setOperationName("send");
		return buildResponse(Status.CREATED, controller.send(request.setup(trxid, body, null)));
	}
	

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{trxid}")
	public Response receive(
			@Parameter(description = "The CICS transaction id", required = true, example = "N612", schema = @Schema(type = SchemaType.STRING)) @PathParam("trxid") @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]{3}") final String trxid,
			@Parameter(description = "The MQ message id", required = true, schema = @Schema(type = SchemaType.STRING)) @QueryParam("key") String key) {
		logger.fine("Received GET for transaction " + trxid + " with key " + key);
		GlobalTracer.get().activeSpan().setOperationName("receive");
		return buildResponse(Status.OK, controller.receive(request.setup(trxid, null, key)));
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{trxid}")
	public Response sendReceive(
			@Parameter(description = "The CICS transaction id", required = true, example = "N612", schema = @Schema(type = SchemaType.STRING)) @PathParam("trxid") @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]{3}") final String trxid,
			@RequestBody(description = "The JSON request body", required = true, content = @Content(mediaType = "application/json", example = "{\"GKSI_HdrBtt\":{\"GL_TRANS_ID\":\"N612\",\"GL_SHAA\":\"130100\",\"GL_TR_NOCHECHI\":\"26062020\",\"GL_TR_ASAKIM\":\"26062020\",\"GL_TR_MARECHET\":\"26062020\",\"SVIVA\":\"T\",\"GL_MISHMERET\":\"0\",\"GL_GIRSA\":\"3400\",\"GL_PEULA\":\"S612\",\"GL_BANK\":\"31\",\"GL_SNIF\":\"284\",\"GL_CH\":\"100633\",\"GL_MAKOR_PU\":\"UE\",\"GL_MAKOR_TAHANA\":\"8\",\"GL_BANK_MARECHET\":\"31\",\"GL_SNIF_MARECHET\":\"284\",\"GL_SNIF_PAKID\":\"284\",\"GL_ZIHUI_PAKID\":\"180025\",\"GL_SAMCHUT_PAKID\":\"5\",\"GL_SNIF_PATUACH\":\"O\",\"SAMCHUT_NIDRESHET\":\"5\",\"GL_GIRSA_BTT\":\"061501\",\"GL_NOSE_ISKI\":\"T\"}}")) final String body) {
		logger.info("Received POST for transaction " + trxid + " body length " + body.length());
		GlobalTracer.get().activeSpan().setOperationName("sendreceive");
		controller.send(request.setup(trxid, body, null));
		return response.getStatus() == 0 ? 
				buildResponse(Status.CREATED, controller.receive(request.setup(trxid, null, new JSONObject(response.getResponse()).getString("key")))) 
				:
				buildResponse(Status.CREATED, response);
	}
	
	private Response buildResponse(Status success, MqbridgeResponse response) {
		if (response.getStatus() == 0) {
			return Response.status(success).entity(response.getResponse()).build();
		} else if (response.getStatus() == -1) {
			return Response.status(Status.REQUEST_TIMEOUT).build();
		} else if (response.getStatus() == -2) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
