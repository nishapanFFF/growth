package com.fabfitfun.shop.api;

import static com.fabfitfun.auth.shared.PermissionLevel.READ;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fabfitfun.auth.shared.PermissionAllowed;
import com.fabfitfun.shop.biz.AuthChecker;
import com.fabfitfun.shop.biz.FacebookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.jbosslog.JBossLog;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fbmetrics")
@JBossLog
public class FacebookResource {

	private final Executor executor;
	private FacebookService facebookMetrics;

	public FacebookResource(FacebookService facebookMetrics, int maxThread) {
		this.facebookMetrics = facebookMetrics;
		this.executor = Executors.newFixedThreadPool(maxThread);
	}

	// These annotations are used to build the Swagger doc
	@Operation(summary = "Returns the calculation object for user storage.", description =
			"Even more details here. User storage is based off of a complex mix of yada yada.")
	@Parameter(in = ParameterIn.PATH, name = "groupId", description = "The user's group id")
	@RequestBody(description = "event data",
	content = @Content(schema = @Schema(implementation = FacebookSubscribeEvent.class)))
	@ApiResponse(description = "status")
	@ApiResponse(responseCode = "400", description = "user not found")
	// These Jersey annotations configure the endpoint
	@POST
	@Path("subscribe")
	@PermissionAllowed(id = AuthChecker.GROWTH_ADMIN_OPERATION, level = READ)
	public FacebookStatus subscribe(@NotNull @Valid FacebookSubscribeEvent event) throws InterruptedException {
		log.infof("Received event");
		executor.execute(() -> {
            try {
                Thread.sleep(5000);
                log.infof("sending subscribe event.");
                boolean result = this.facebookMetrics.subscribe(event);
            } catch (InterruptedException e) {
            	log.infof("subscribe caught: " + e.getMessage());
            }
        });
		return new FacebookStatus(true);
	}
}
