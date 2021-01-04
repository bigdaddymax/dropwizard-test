package com.example.helloworld.db;

import io.dropwizard.hibernate.AbstractDAO;
import com.example.helloworld.core.Photo;

import org.hibernate.SessionFactory;

import org.hibernate.query.Query;


import java.util.List;
import java.util.Optional;

public class PhotoDAO extends AbstractDAO<Photo> {
    public PhotoDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Photo> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Photo findByHash(String hash) {
        return  (Photo) namedQuery("com.example.helloworld.core.Photo.findByHash").setParameter("hash", hash).uniqueResult();
    }

    public Photo create(Photo photo) {
        return persist(photo);
    }

    @SuppressWarnings("unchecked")
    public List<Photo> findAll() {
        return list((Query<Photo>) namedQuery("com.example.helloworld.core.Photo.findAll"));
    }

}
