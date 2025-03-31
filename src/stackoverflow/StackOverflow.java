package stackoverflow;

import java.util.List;

public class StackOverflow {
    private final UserService userService;
    private final QuestionService questionService;
    private final TagService tagService;
    private final AnswerService answerService;
    private final CommentService commentService;
    private static StackOverflow stackOverflow;

    private StackOverflow() {
        userService = UserService.getUserService();
        answerService = AnswerService.getAnswerService();
        tagService = TagService.getTagService();
        questionService = QuestionService.getQuestionService();
        commentService = CommentService.getCommentService();

    }

    public StackOverflow startStackOverflow() {
        if (stackOverflow == null) {
            synchronized (StackOverflow.class) {
                if (stackOverflow == null) {
                    stackOverflow = new StackOverflow();
                }
            }
        }
        return stackOverflow;
    }
    public Member createUser(String username, String password) {
        return userService.createUser(username,password);
    }
    public Question askQuestion(Member user, String title, String content, List<String> tags) {
        tagService.addTags(tags);
        return questionService.askQuestion(user, title, content, tags);
    }

    public Answer answerQuestion(Member user, Question question, String content) {
        return answerService.answerQuestion(user,question,content);
    }

    public void voteQuestion(Member user, Question question, int value) {
        questionService.voteQuestion(user,question,value);
    }

    public void voteAnswer(Member user, Answer answer, int value) {
        answerService.voteAnswer(user,answer,value);
    }

    public void acceptAnswer(Answer answer) {
        answerService.acceptAnswer(answer);
    }

    public void addComment(Member user, Commentable commentable,String content) {
        commentService.addComment(user,commentable,content);
    }
    public List<Question> searchQuestions(String query){
        return questionService.search(query);
    }

    public List<Question> getQuestionsByUser(Member user) {
        return questionService.getQuestionsbyUser(user);
    }

}
