package com.fabfitfun.abtest.api;

import static com.fabfitfun.auth.shared.PermissionIds.BACKEND_OPERATION;
import com.fabfitfun.auth.shared.PermissionAllowed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.jbosslog.JBossLog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

import static com.fabfitfun.auth.shared.PermissionLevel.READ;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/abtest")
@JBossLog
public class AbtestResource {

	private com.fabfitfun.abtest.biz.AbtestService abtestService;

	public AbtestResource(com.fabfitfun.abtest.biz.AbtestService abtestService) {
		this.abtestService = abtestService;
	}

	// These annotations are used to build the Swagger doc
	@Operation(summary = "Returns the calculation object for user storage.", description =
			"Even more details here. User storage is based off of a complex mix of yada yada.")
	@Parameter(in = ParameterIn.PATH, name = "groupId", description = "The user's group id")
	@RequestBody(description = "event data",
	content = @Content(schema = @Schema()))
	@ApiResponse(description = "status")
	@ApiResponse(responseCode = "400", description = "user not found")
	// These Jersey annotations configure the endpoint
	@POST
	@Path("addchannel/{channelName}")
	@PermissionAllowed(id = BACKEND_OPERATION, level = READ)
	public boolean addChannel(@PathParam("channelName") String channelName) throws ParseException {
        this.abtestService.addChannel(channelName);
        return true;
	}
}
