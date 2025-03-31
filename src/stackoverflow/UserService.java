package stackoverflow;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private static UserService userService;
    private String username;
    private String password;
    private String accountId;
    private AccountType accountType;
    private String accountStatus;
    private Map<String,Account> accounts;

    private UserService() {
        this.accounts = new ConcurrentHashMap<>();
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    public Member createUser(String username, String password) {
        accountId = UUID.randomUUID().toString();
        accountType = AccountType.MEMBER;
        Account account = new Account(username, password, accountId, accountType);
        Member member = new Member(account);
        accounts.put(username, account);
        System.out.println("Registered new account, please login");
        return member;
    }

    private Member login(String username, String password) {
        if(accounts.containsKey(username)) {
            Account account = accounts.get(username);
            if(account.verifyPassword(password)){
                return new Member(account);
            }

        }
        else{
            System.out.println("Invalid username or password");
        }
        return null;
    }

}
