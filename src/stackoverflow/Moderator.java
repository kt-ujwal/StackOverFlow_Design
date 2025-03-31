package stackoverflow;

public class Moderator extends Member {

    public Moderator(Account account) {
        super(account);
    }

    public boolean undeleteQuestion(Question question) {
        return true;
    }

    public boolean deleteQuestion(Question question) {
        return true;
    }

    public boolean deleteAnswer(Answer answer) {
        return true;
    }

    public boolean undeleteAnswer(Answer answer) {
        return true;
    }

    public boolean closeQuestion(Question question) {
        return true;
    }
}
