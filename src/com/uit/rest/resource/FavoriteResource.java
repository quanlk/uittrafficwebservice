package com.uit.rest.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.uit.rest.business.FavoriteBO;
import com.uit.rest.business.FavoriteBOImpl;
import com.uit.rest.model.Favorite;
import com.uit.rest.model.FavoriteListAdapter;

@Path("/favorite")
public class FavoriteResource {

	private static final String ID = "id";
	private static final String ACCOUNT_ID = "accountID";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String ADDRESS = "address";
	private static final String DESCRIPTION = "description";
	
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
	@Path("/get/{ACCOUNTID}")
	@Produces(MediaType.APPLICATION_JSON)
	public FavoriteListAdapter getList(@PathParam("ACCOUNTID") final long accountId) {
		ArrayList<Object> list = new ArrayList<Object>();
		FavoriteListAdapter result = new FavoriteListAdapter();
		FavoriteBO bo = new FavoriteBOImpl();
		
		list.addAll(bo.getList(accountId));
		result.setList(list);
		
		return result;
	}
	
	// Use data from the client source to create a new LocationInfo object, returned in JSON format.
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response add(MultivaluedMap<String, String> params) {
		long accountID = Integer.parseInt(params.getFirst(ACCOUNT_ID));
		double latitude = Double.parseDouble(params.getFirst(LATITUDE));
		double longitude = Double.parseDouble(params.getFirst(LONGITUDE));
		String address = params.getFirst(ADDRESS);
		String description = params.getFirst(DESCRIPTION);
		Favorite favorite = null;
		FavoriteBO bo = new FavoriteBOImpl();
		String result = "";
		
		favorite = new Favorite(0, accountID, latitude, longitude, address, description);
		System.out.println(favorite.getAddress());
		
		result = (bo.add(favorite)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response delete(MultivaluedMap<String, String> params) {
		FavoriteBO bo = new FavoriteBOImpl();
		long id = Long.valueOf(params.getFirst(ID));
		
		String result = (bo.delete(id)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
}
