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
    public String getText() {
        return text;
    }

    @JsonProperty
    public void setText(String text) {
        this.text = text;
    }
}

