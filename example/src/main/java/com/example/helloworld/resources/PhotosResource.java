package com.example.helloworld.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;

@Path("photos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PhotosResource {
	private final PhotoStore store;

	public PhotosResource(PhotoStore store) {
	    this.store = store;
	}

	@GET
	public PhotosList fetch(@QueryParam("count") @DefaultValue("20") OptionalInt count) {
	    final List<Photo> photos = store.fetch(count.get());
	    if (photos == null) {
		throw new WebApplicationException(Status.NOT_FOUND);
	    }

	    return new PhotosList(photos);
	}
}
