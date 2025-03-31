package stackoverflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Question implements Votable, Commentable {
    private final String title;
    private final String content;
    private List<Tag> tags;
    private final String questionId;
    private List<Comment> questionComments;
    private List<Answer> questionAnswers;
    private final Member author;
    private final List<Vote> votes;
    private final Date creationDate;


    public Question(Member member, String title, String content, List<String> tags) {
        this.author = member;
        this.title = title;
        this.content = content;
        this.creationDate = new Date();
        this.questionId = generateID();
        this.questionComments = new ArrayList<>();
        this.questionAnswers = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.tags = new ArrayList<>();
        for (String tag : tags) {
            this.tags.add(new Tag(tag));
        }
    }



    public void addAnswer(Answer answer){
        this.questionAnswers.add(answer);
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void  addTag(String tagName){
        tags.add(new Tag(tagName));
    }
    public void deleteTag(String tagName){
        tags.removeIf(tag -> tag.getName().equals(tagName));
    }
    private String generateID() {
        return UUID.randomUUID().toString();
    }

    public String getQuestionId(){
        return questionId;
    }

    @Override
    public void addComment(Comment comment) {
        this.questionComments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(questionComments);
    }

    @Override
    public void vote(Member user, int value) {
    if(value != 1 && value!=-1){
        throw new IllegalArgumentException("Vote must be 1 or -1");
    }
    votes.removeIf(v->v.getUser().equals(user));
    votes.add(new Vote(user, value));
    author.updateReputation(value * DefaultReputation.QUESTION_REPUTATION);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getVoteCount).sum();
    }
}
