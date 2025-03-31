package stackoverflow;

import java.util.UUID;

public class Account {
    final String name;
    String password;
    final String accountId;
    AccountStatus status;
    AccountType accountType;
    private int reputation;
    public Account(String name, String password, String accountId, AccountType accountType) {
        this.name = name;
        this.password = password;
        this.accountId = accountId;
        this.accountType = accountType;
        this.status = AccountStatus.ACTIVE;
    }



    public boolean resetPassword(String password) {
        return true;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }


}
