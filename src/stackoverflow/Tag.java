package stackoverflow;

import java.util.UUID;

public class Tag {
    private final String tagId;
    private final String tagName;

    public Tag(String tagName){
        this.tagName = tagName;
        this.tagId = generateID();
    }
    private String generateID() {
        return UUID.randomUUID().toString();
    }
    public String getId() { return tagId; }
    public String getName() { return tagName; }
}
