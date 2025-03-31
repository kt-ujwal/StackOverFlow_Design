package stackoverflow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnswerService {
    private final Map<String, Answer> answers;
    private static AnswerService answerService;
    private AnswerService() {
        answers = new ConcurrentHashMap<>();
    }

    public static AnswerService getAnswerService() {
        if (answerService == null) {
            synchronized (AnswerService.class) {
                if (answerService == null) {
                    answerService = new AnswerService();
                }
            }
        }
        return answerService;
    }

    public Answer answerQuestion(Member user, Question question, String content) {
        Answer answer = user.answerQuestion(question, content);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public void voteAnswer(Member user, Answer answer, int value) {
        answer.vote(user, value);
    }
    public void acceptAnswer(Answer answer) {
        answer.markAsAccepted();
    }

}
