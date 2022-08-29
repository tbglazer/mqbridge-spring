package il.co.fibi.comm.mqbridge.rest.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import com.ibm.websphere.logging.hpel.LogRecordContext;

@Provider
public class LoggingFilter implements ContainerRequestFilter  {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String user = requestContext.getHeaderString("remote_user");
		if (user != null) {
			LogRecordContext.addExtension("user", user);
		}
		else {
			LogRecordContext.removeExtension("user");
		}
	}
}
