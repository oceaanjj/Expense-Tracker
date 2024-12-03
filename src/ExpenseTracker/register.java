package ExpenseTracker;
public class register extends Database {

    public void addEmail(String email){
        this.email = email;
    }

    public void addPass(String password){
        this.password = password;
    }

    public void addNickname (String nickname){
        this.nickname = nickname;
    }
    
    public void Income(double income){
        this.income = income;

        super.write(email,password,nickname, income);
    }
}
