package stackoverflow;

public interface Votable {
    void vote(Member user, int value);
    int getVoteCount();
}
