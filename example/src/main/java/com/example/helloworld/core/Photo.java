package com.example.helloworld.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "photo")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.example.helloworld.core.Photo.findAll",
                        query = "SELECT p FROM Photo p"
                )
        })
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "hash", nullable = false)
    @NotNull
    private String hash;

    @Column(name = "path", nullable = false)
    @NotNull
    private String path;

    @Column(name = "dateTaken", nullable = false)
    @NotNull
    private LocalDateTime dateTaken;

    @Column(name = "dateSaved", nullable = false)
    private LocalDateTime dateSaved;

    public Photo() {
    }

    public Photo(String name, String path, String hash, LocalDateTime dateTaken) {
        this.name = name;
        this.hash = hash;
        this.path = path;
        this.dateTaken = dateTaken;
        this.dateSaved = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Photo)) {
            return false;
        }

        Photo photo = (Photo) o;

        return id == photo.id ||
                hash == photo.hash ||
                Objects.equals(dateTaken, photo.dateTaken) &&
                Objects.equals(name, photo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}
