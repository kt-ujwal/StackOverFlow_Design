package stackoverflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Answer implements Votable,Commentable{
    private final String content;
    private final Member author;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;
    private final String answerId;

    public Answer(String content, Member author, Question question) {
        this.content = content;
        this.author = author;
        this.question = question;
        this.isAccepted = false;
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.answerId = generateID();
    }
    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void vote(Member user, int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("Vote value must be either 1 or -1");
        }
        votes.removeIf(v -> v.getUser().equals(user));
        votes.add(new Vote(user, value));
        author.updateReputation(value * DefaultReputation.ANSWER_REPUTATION);  // +10 for upvote, -10 for downvote
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getVoteCount).sum();
    }

    private String generateID(){
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return answerId;
    }

    public void markAsAccepted() {
        this.isAccepted = true;
    }
}
