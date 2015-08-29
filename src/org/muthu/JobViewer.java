package org.muthu;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.sun.jersey.api.view.Viewable;

@Path("/jobview")
public class JobViewer{
	
	@GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable doIndex() throws Exception {
    	return new Viewable("/login.jsp");
	}
	
}
 
