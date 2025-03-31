package stackoverflow;

public class Vote{
    private final int value;
    private final Member user;

    public Vote(Member user, int value) {
        this.user = user;
        this.value = value;
    }


    public int getVoteCount() {
        return value;
    }
    public Member getUser() {
        return user;
    }
}
