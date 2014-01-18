package com.uit.rest.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.uit.rest.business.IssueBO;
import com.uit.rest.business.IssueBOImpl;
import com.uit.rest.model.Issue;
import com.uit.rest.model.IssueListAdapter;

@Path("/issue")
public class IssueResource {

	private static final String ID = "id";
	private static final String ACCOUNT_ID = "accountID";
	private static final String REPORT_ID = "reportID";
	private static final String MESSAGE = "message";
	private static final String PAGE = "page";
	
	// The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information.
	@Context
	UriInfo uriInfo;
	
	// Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
	@Context
	Request request;
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(MultivaluedMap<String, String> params) {
		Issue issue = new Issue();
		IssueBO bo = new IssueBOImpl();
		long accountId = Long.valueOf(params.getFirst(ACCOUNT_ID));
		long reportId = Long.valueOf(params.getFirst(REPORT_ID));
		String message = params.getFirst(MESSAGE);
		
		issue.setAccountId(accountId);
		issue.setReportId(reportId);
		issue.setMessage(message);
		
		String result = (bo.add(issue)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/get-list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public IssueListAdapter getList(MultivaluedMap<String, String> params) {
		int page = Integer.valueOf(params.getFirst(PAGE));
		ArrayList<Object> list = new ArrayList<Object>();
		IssueListAdapter result = new IssueListAdapter();
		IssueBO bo = new IssueBOImpl();
		
		list.addAll(bo.getList(page));
		
		result.setList(list);
		
		return result;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(MultivaluedMap<String, String> params) {
		IssueBO bo = new IssueBOImpl();
		long id = Long.valueOf(params.getFirst(ID));
		
		String result = (bo.delete(id)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
}
