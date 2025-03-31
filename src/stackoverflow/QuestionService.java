package stackoverflow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class QuestionService implements Search{
    private static QuestionService questionService;
    private final Map<String, Question> questions;
    private QuestionService() {
        this.questions = new ConcurrentHashMap<>();
    }

    public static QuestionService getQuestionService() {
        if (questionService == null) {
            synchronized (QuestionService.class) {
                if (questionService == null) {
                    questionService = new QuestionService();
                }
            }
        }
        return questionService;
    }

    public Question askQuestion(Member user, String title, String content, List<String> tags){
        Question question = new Question(user,title,content,tags);
        questions.putIfAbsent(question.getQuestionId(), question);
        return question;
    }

    @Override
    public List<Question> search(String query) {
        return questions.values().stream()
                .filter(q->q.getTitle().toLowerCase().contains(query.toLowerCase())||
                        q.getContent().toLowerCase().contains(query.toLowerCase())||
                        q.getTags().stream().anyMatch(t->t.getName().equalsIgnoreCase(query)))
                .collect(Collectors.toList());
    }

    public void voteQuestion(Member user, Question question, int value) {
        question.vote(user, value);
    }

    public List<Question> getQuestionsbyUser(Member user) {
        return user.getQuestions();
    }


}
