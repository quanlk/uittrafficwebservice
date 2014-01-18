package com.uit.rest.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.uit.rest.business.AccountBO;
import com.uit.rest.business.AccountBOImpl;
import com.uit.rest.model.Account;
import com.uit.rest.model.AccountListAdapter;

@Path("/account")
public class AccountResource {

	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String ROLE = "role";
	private static final String PAGE = "page";
	private static final String KEYWORD = "keyword";
	
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
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		return Response.status(200).entity("TEST").build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Account get(MultivaluedMap<String, String> params) {
		Account account = new Account();
		AccountBO bo = new AccountBOImpl();
		String username = params.getFirst(USERNAME);
		
		account = bo.get(username);
		
		return (account != null) ? account : new Account();
	}
	
	// Use data from the client source to create a new LocationInfo object, returned in JSON format.
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response update(MultivaluedMap<String, String> params) {
		Account account = null;
		long id = Long.parseLong(params.getFirst(ID));
		String username = params.getFirst(USERNAME);
		String password = params.getFirst(PASSWORD);
		String phoneNumber = params.getFirst(PHONE_NUMBER);
		String role = params.getFirst(ROLE);
		AccountBO bo = new AccountBOImpl();
		
		account = new Account(id, username, password, phoneNumber, role);
		
		String result = (bo.update(account)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	// Use data from the client source to create a new LocationInfo object, returned in JSON format.
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response add(MultivaluedMap<String, String> params) {
		Account account = null;
		String username = params.getFirst(USERNAME);
		String password = params.getFirst(PASSWORD);
		String phoneNumber = params.getFirst(PHONE_NUMBER);
		String role = params.getFirst(ROLE);
		AccountBO bo = new AccountBOImpl();
		
		account = new Account(0, username, password, phoneNumber, role);
		
		String result = (bo.add(account)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/get-list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public AccountListAdapter getList(MultivaluedMap<String, String> params) {
		int page = Integer.valueOf(params.getFirst(PAGE));
		ArrayList<Object> list = new ArrayList<Object>();
		AccountListAdapter result = new AccountListAdapter();
		AccountBO bo = new AccountBOImpl();
		
		list.addAll(bo.getList(page));
		
		result.setList(list);
		
		return result;
	}
	
	// Use data from the client source to create a new LocationInfo object, returned in JSON format.
	@POST
	@Path("/change-role")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response changeRole(MultivaluedMap<String, String> params) {
		long id = Long.parseLong(params.getFirst(ID));
		AccountBO bo = new AccountBOImpl();
		
		String result = (bo.changeRole(id)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public AccountListAdapter search(MultivaluedMap<String, String> params) {
		String keyword = params.getFirst(KEYWORD);
		ArrayList<Object> list = new ArrayList<Object>();
		AccountListAdapter result = new AccountListAdapter();
		AccountBO bo = new AccountBOImpl();
		
		list.addAll(bo.search(keyword));
		
		result.setList(list);
		
		return result;
	}
}
