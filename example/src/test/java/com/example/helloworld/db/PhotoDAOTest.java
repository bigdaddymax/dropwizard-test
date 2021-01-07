package com.example.helloworld.db;

import com.example.helloworld.core.Photo;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class PhotoDAOTest {

    public DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
        .addEntityClass(Photo.class)
        .build();

    private PhotoDAO photoDAO;

    @BeforeEach
    public void setUp() throws Exception {
        photoDAO = new PhotoDAO(daoTestRule.getSessionFactory());
    }

    @Test
    public void createPhoto() {
        final Photo me = daoTestRule.inTransaction(() -> photoDAO.create(new Photo("me.jpg", "/path/to/photo", "123abc", LocalDateTime.parse("2020-01-01T10:20:11"))));
        assertThat(me.getId()).isPositive();
        assertThat(me.getName()).isEqualTo("me.jpg");
        assertThat(me.getPath()).isEqualTo("/path/to/photo");
        assertThat(me.getHash()).isEqualTo("123abc");
        assertThat(photoDAO.findById(me.getId())).isEqualTo(Optional.of(me));
    }

    @Test
    public void findAll() {
        daoTestRule.inTransaction(() -> {
            photoDAO.create(new Photo("me.jpg", "/path/to/photo1", "123abc", LocalDateTime.parse("2020-01-01T10:10:10")));
            photoDAO.create(new Photo("kid.jpg", "/path/to/photo2", "432bdf", LocalDateTime.parse("2019-10-10T20:20:20")));
            photoDAO.create(new Photo("mom.jpg", "/path/to/photo3", "abc432", LocalDateTime.parse("2018-11-01T01:10:11")));
        });

        final List<Photo> photos = photoDAO.findAll();
        assertThat(photos).extracting("name").containsOnly("me.jpg", "kid.jpg", "mom.jpg");
        assertThat(photos).extracting("path").containsOnly("/path/to/photo1", "/path/to/photo2", "/path/to/photo3");
        assertThat(photos).extracting("hash").containsOnly("123abc", "432bdf", "abc432");
    }

    @Test
    public void findByHash() {
        daoTestRule.inTransaction(() -> {
            photoDAO.create(new Photo("me.jpg", "/path/to/photo1", "123abc", LocalDateTime.parse("2020-01-01T10:10:10")));
            photoDAO.create(new Photo("kid.jpg", "/path/to/photo2", "432bdf", LocalDateTime.parse("2019-10-10T20:20:20")));
            photoDAO.create(new Photo("mom.jpg", "/path/to/photo3", "abc432", LocalDateTime.parse("2018-11-01T01:10:11")));
        });

        final Optional<Photo> photo = photoDAO.findByHash("123abc");
        assertThat(photo.get().getName()).isEqualTo("me.jpg");        
    }
}
