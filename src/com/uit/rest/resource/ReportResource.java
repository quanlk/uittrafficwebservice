package com.uit.rest.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

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

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.uit.rest.business.ReportBO;
import com.uit.rest.business.ReportBOImpl;
import com.uit.rest.model.Report;
import com.uit.rest.model.ReportListAdapter;

@Path("/report")
public class ReportResource {

	private static final String ID = "id";
	private static final String ACCOUNT_ID = "accountID";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String REPORT_TYPE_ID = "reportTypeID";
	private static final String WARNING_ID = "warningID";
	private static final String DESCRIPTION = "description";
	private static final String REGION = "region";
	private static final String IMAGE_URL = "imageUrl";
	private static final String VIDEO_URL = "videoUrl";
	private static final String REPORT_ID = "reportID";
	private static final String LIMIT = "limit";
	
	private static final String UPLOAD_PHOTO_LOCATION = "/Photo/";
	private static final String UPLOAD_VIDEO_LOCATION = "/Video/";
	//private static final String UPLOAD_PHOTO_LOCATION = "/TrafficWebService/";
	//private static final String UPLOAD_VIDEO_LOCATION = "/Video/";
	
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
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSamplePerson() {
		return "Test";
	}

	
	@POST
	@Path("/get-list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ReportListAdapter getList(MultivaluedMap<String, String> params) {
		double latitude = Double.parseDouble(params.getFirst(LATITUDE));
		double longitude = Double.parseDouble(params.getFirst(LONGITUDE));
		String region = params.getFirst(REGION);
		String reportTypeID = params.getFirst(REPORT_TYPE_ID);
		double limit = Double.parseDouble(params.getFirst(LIMIT));
		
		ArrayList<Object> list = new ArrayList<Object>();
		ReportListAdapter result = new ReportListAdapter();
		ReportBO bo = new ReportBOImpl();
		
		if (("").equals(reportTypeID)) {
			list.addAll(bo.getList(latitude, longitude, region, limit, true));
		} else {
			list.addAll(bo.getListByReportType(
					latitude, longitude, region, Long.parseLong(reportTypeID), limit));
		}
		
		result.setList(list);
		
		return result;
	}
	
	@POST
	@Path("/get-invalid-list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ReportListAdapter getInvalidList(MultivaluedMap<String, String> params) {
		String region = params.getFirst(REGION);
		
		ArrayList<Object> list = new ArrayList<Object>();
		ReportListAdapter result = new ReportListAdapter();
		ReportBO bo = new ReportBOImpl();
		
		list.addAll(bo.getList(0, 0, region, 0, false));
		
		result.setList(list);
		
		return result;
	}
	
	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Report get(MultivaluedMap<String, String> params) {
		long id = Long.valueOf(params.getFirst(ID));
		Report result = new Report();
		ReportBO bo = new ReportBOImpl();
		
		result = bo.get(id);
		
		return result;
	}
	
	@GET
	@Path("/get/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Report get(@PathParam("ID") final long id) {
		Report result = new Report();
		ReportBO bo = new ReportBOImpl();
		
		result = bo.get(id);
		
		return result;
	}
	
	// Use data from the client source to create a new LocationInfo object, returned in JSON format.
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ReportListAdapter add(MultivaluedMap<String, String> params) {
		long accountId = Long.parseLong(params.getFirst(ACCOUNT_ID));
		double latitude = Double.parseDouble(params.getFirst(LATITUDE));
		double longitude = Double.parseDouble(params.getFirst(LONGITUDE));
		long reportTypeId = Long.parseLong(params.getFirst(REPORT_TYPE_ID));
		long warningId = Long.parseLong(params.getFirst(WARNING_ID));
		String description = params.getFirst(DESCRIPTION);
		String region = params.getFirst(REGION);
		String imageUrl = params.getFirst(IMAGE_URL);
		String videoUrl = params.getFirst(VIDEO_URL);
		double limit = Double.parseDouble(params.getFirst(LIMIT));
		
		Report report = new Report();
		ArrayList<Object> list = new ArrayList<Object>();
		ReportListAdapter result = new ReportListAdapter();
		ReportBO bo = new ReportBOImpl();
		
		System.out.println("Storing posted " + latitude + "," + longitude);
		
		report.setAccountId(accountId);
		report.setLatitude(latitude);
		report.setLongitude(longitude);
		report.setReportTypeId(reportTypeId);
		report.setWarningId(warningId);
		//locationInfo.setTime(time);
		report.setDescription(description);
		report.setRegion(region);
		report.setImageUrl(imageUrl);
		report.setVideoUrl(videoUrl);
		
		//list.add(report);
		bo.add(report);
		list.addAll(bo.getList(latitude, longitude, region, limit, true));
		result.setList(list);
		
		return result;
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response update(MultivaluedMap<String, String> params) {
		long id = Long.valueOf(params.getFirst(ID));
		long accountId = Long.valueOf(params.getFirst(ACCOUNT_ID));
		long warningId = Long.valueOf(params.getFirst(WARNING_ID));
		String description = params.getFirst(DESCRIPTION);
		String imageUrl = params.getFirst(IMAGE_URL);
		String videoUrl = params.getFirst(VIDEO_URL);
		Report report = new Report();
		ReportBO bo = new ReportBOImpl();
		String result = "";
		
		report.setId(id);
		report.setAccountId(accountId);
		report.setWarningId(warningId);
		report.setDescription(description);
		report.setImageUrl(imageUrl);
		report.setVideoUrl(videoUrl);
		
		result = (bo.update(report)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/get-amount/{LATITUDE}/{LONGITUDE}/{REGION}/{LIMIT}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAmount(
			@PathParam("LATITUDE") final double latitude,
			@PathParam("LONGITUDE") final double longitude,
			@PathParam("REGION") final String region,
			@PathParam("LIMIT") final double limit) {
		
		Map<Long, Integer> result = null;
		ReportBO bo = new ReportBOImpl();
		
		result = bo.getAmountOfReport(latitude, longitude, region, limit);
		
		return result.toString();
	}
	
	@GET
	@Path("/verify/{ACCOUNTID}/{REPORTID}")
	public Response verifyReportIsThanked(
			@PathParam("ACCOUNTID") final long accountId,
			@PathParam("REPORTID") final long reportId) {
		ReportBO bo = new ReportBOImpl();
		String result = "";
		
		result = (bo.isThanked(accountId, reportId)) ? "true" : "";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/thank")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response thank(MultivaluedMap<String, String> params) {
		long accountId = Long.valueOf(params.getFirst(ACCOUNT_ID));
		long reportId = Long.valueOf(params.getFirst(REPORT_ID));
		ReportBO bo = new ReportBOImpl();
		String result = "";
		
		result = (bo.increaseThanksCounter(accountId, reportId)) ? "true" : "";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/upload-photo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadPhoto(
			@FormDataParam("photo") InputStream uploadedInputStream,
			@FormDataParam("photo") FormDataContentDisposition fileDetail) {
		String timeStamp = 
				new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());
		String fileName = timeStamp 
				+ fileDetail.getFileName().substring(fileDetail.getFileName().indexOf("."));
		String uploadedImageLocation = UPLOAD_PHOTO_LOCATION + fileName;
		
		return (saveToFile(uploadedInputStream, uploadedImageLocation)) 
				? Response.status(200).entity(fileName).build()
				: Response.status(200).entity("").build();
	}
	
	@POST
	@Path("/upload-video")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadVideo(
			@FormDataParam("video") InputStream uploadedInputStream,
			@FormDataParam("video") FormDataContentDisposition fileDetail) {
		String timeStamp = 
				new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());
		String fileName = timeStamp 
				+ fileDetail.getFileName().substring(fileDetail.getFileName().indexOf("."));
		String uploadedVideoLocation = UPLOAD_VIDEO_LOCATION + fileName;
		
		return (saveToFile(uploadedInputStream, uploadedVideoLocation)) 
				? Response.status(200).entity(fileName).build()
				: Response.status(200).entity("").build();
	}
	
	@GET
	@Path("/get-file/{FILEPATH}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("FILEPATH") final String filePath) {
		File file = null;
		
		if (filePath.contains("JPG")) {
			file = new File("/Photo/" + filePath);
		} else {
			file = new File("/Video/" + filePath);
		}
		
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM).build();
	}
	
	@POST
	@Path("/deactivate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deactivate(MultivaluedMap<String, String> params) {
		long id = Long.valueOf(params.getFirst(ID));
		ReportBO bo = new ReportBOImpl();
		String result = "";
		
		result = (bo.deactivate(id)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/validate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response validate(MultivaluedMap<String, String> params) {
		long id = Long.valueOf(params.getFirst(ID));
		ReportBO bo = new ReportBOImpl();
		String result = "";
		
		result = (bo.validate(id)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response delete(MultivaluedMap<String, String> params) {
		long id = Long.valueOf(params.getFirst(ID));
		ReportBO bo = new ReportBOImpl();
		String result = "";
		
		result = (bo.delete(id)) ? "SUCCESS" : "FAIL";
		
		return Response.status(200).entity(result).build();
	}
	
	private boolean saveToFile(InputStream uploadedInputStream, String uploadedLocation) {
		try {
			OutputStream out = null;
			int read = 0;
			byte[] bytes = new byte[1024];
			
			out = new FileOutputStream(new File(uploadedLocation));
			
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			out.flush();
			out.close();
			
			return true;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}
}
