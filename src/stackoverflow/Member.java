package stackoverflow;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final Account account;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private int reputation;


    public Member(Account account) {
        this.account = account;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.reputation = 0;
    }

    public Question askQuestion(String title, String content, List<String> tags){
        Question question = new Question(this,title,content,tags);
        questions.add(question);
        updateReputation(DefaultReputation.QUESTION_REPUTATION);
    return question;
    }

    public Answer answerQuestion(Question question, String content) {
        Answer answer = new Answer(content, this, question);
        answers.add(answer);
        question.addAnswer(answer);
        updateReputation(DefaultReputation.ANSWER_REPUTATION); // Gain 10 reputation for answering
        return answer;
    }

    public Comment addComment(Commentable commentable, String content) {
        Comment comment = new Comment(this, content);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(DefaultReputation.COMMENT_REPUTATION); // Gain 2 reputation for commenting
        return comment;
    }

    public void updateReputation(int value) {
        this.reputation += value;
        if(this.reputation<0){
            this.reputation = 0;
        }
    }

    public String getId() { return this.account.accountId; }
    public String getUsername() { return this.account.name; }
    public int getReputation() { return reputation; }
    public List<Question> getQuestions() { return new ArrayList<>(questions); }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Comment> getComments() { return new ArrayList<>(comments); }
}
