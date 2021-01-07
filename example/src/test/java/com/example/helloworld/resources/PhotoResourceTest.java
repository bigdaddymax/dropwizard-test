package com.example.helloworld.resources;

import com.example.helloworld.core.Photo;
import com.example.helloworld.db.PhotoDAO;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link PhotoResource}.
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class PhotoResourceTest {
    private static final PhotoDAO PHOTO_DAO = mock(PhotoDAO.class);
    public static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new PhotosResource(PHOTO_DAO))
            .build();
    private ArgumentCaptor<Photo> photoCaptor = ArgumentCaptor.forClass(Photo.class);
    private Photo photo;

    @BeforeEach
    public void setUp() {
        photo = new Photo();
        photo.setName("Full Name");
        photo.setPath("Job Title");
        photo.setHash("1995");
        photo.setDateTaken(LocalDateTime.parse("2020-10-01T10:10:10"));
    }

    @AfterEach
    public void tearDown() {
        reset(PHOTO_DAO);
    }

    @Test
    public void createPhoto() {
        when(PHOTO_DAO.create(any(Photo.class))).thenReturn(photo);
        final Response response = RESOURCES.target("/photos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(photo, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(PHOTO_DAO).create(photoCaptor.capture());
        assertThat(photoCaptor.getValue()).isEqualTo(photo);
    }

    @Test
    public void createPhotoFailureNoName() {
        photo.setName(null);
        final Response response = RESOURCES.target("/photos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(photo, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isNotEqualTo(Response.Status.OK);
        assertThat(response.readEntity(String.class)).contains("name must not be null");
    }

    @Test
    public void createPersonFailureNoPath() {
        photo.setPath(null);
        final Response response = RESOURCES.target("/photos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(photo, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isNotEqualTo(Response.Status.OK);
        assertThat(response.readEntity(String.class)).contains("path must not be null");
    }

    @Test
    public void listPhotos() throws Exception {
        final List<Photo> photos = Collections.singletonList(photo);
        when(PHOTO_DAO.findAll()).thenReturn(photos);

        final List<Photo> response = RESOURCES.target("/photos")
            .request().get(new GenericType<List<Photo>>() {
            });

        verify(PHOTO_DAO).findAll();
        assertThat(response).containsAll(photos);
    }

    @Test
    public void findByHash() {
        when(PHOTO_DAO.findByHash("abc123")).thenReturn(Optional.of(photo));
        final Photo response = RESOURCES.target("/photos/abc123")
            .request().get(new GenericType<Photo>() {
            });
        verify(PHOTO_DAO).findByHash("abc123");
        assertThat(response).isEqualTo(photo);
    }
}
