package stackoverflow;

public class Admin {
    private AccountStatus status;
    Admin(AccountStatus status) {
        this.status = status;
    }
    public boolean blockMember(Member member) {
        return true;
    }

    public boolean unblockMember(Member member) {
        return true;
    }
}
