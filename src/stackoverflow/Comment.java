package stackoverflow;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private final String commentId;
    private final String content;
    private final Member author;
    private final Date creationDate;

    public Comment(Member author, String content) {
        this.author = author;
        this.content = content;
        this.creationDate = new Date();
        this.commentId = generateID();

    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }

    public String getId() { return commentId; }
    public Member getAuthor() { return author; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }


}
