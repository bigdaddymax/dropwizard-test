package com.example.helloworld.resources;

import com.example.helloworld.db.PhotoDAO;
import com.example.helloworld.core.Photo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;
import io.dropwizard.hibernate.UnitOfWork;
import javax.validation.Valid;

@Path("photos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PhotosResource {
	private final PhotoDAO store;

	public PhotosResource(PhotoDAO store) {
	    this.store = store;
	}

	@GET
	@UnitOfWork
	public List<Photo> listPhotos() {
		return store.findAll();
	}

	@POST
	@UnitOfWork
	public Photo createPhoto(@Valid Photo photo) {
		return store.create(photo);
	}
}
