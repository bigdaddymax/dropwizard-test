package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class Photo {
    private String hash;
    private String path;
    private String name;
    private LocalDateTime dateTaken;
    private LocalDateTime dateStored;

    public Photo(String name, String path, String hash, LocalDateTime dateTaken, LocalDateTime dateStored) {
        this.name = name;
 	this.path = path;
	this.hash = hash;
	this.dateTaken = dateTaken;
	this.dateStored = dateStored;
    }

    @JsonProperty
    public String getHash() {
        return hash;
    }

    @JsonProperty
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty
    public String getPath() {
        return path;
    }

    @JsonProperty
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    @JsonProperty
    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }

    @JsonProperty
    public LocalDateTime getDateStored() {
        return dateStored;
    }

    @JsonProperty
    public void setDateStored(LocalDateTime dateStored) {
        this.dateStored = dateStored;
    }

}

