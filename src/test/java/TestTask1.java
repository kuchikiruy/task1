import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.study.account.Account;

public class TestTask1 {


    Account acc = new Account("Alex");
    @Test
    @DisplayName("Создаем аккаунты")
    public void testCreateAccount(){
        acc.setName("IVAN");
        try {
            Account acc2 = new Account(null);
            Account acc3 = new Account("");
        } catch (IllegalArgumentException e) {
            System.out.println("норм");
        }


    }
    @Test
    public void testMoney() {
        acc.updateMoney("RUR", 50);
        acc.updateMoney("EUR", 50);
        System.out.println(acc.getMoney());
        acc.updateMoney("RUR", 100);
        System.out.println(acc.getMoney("RUR"));
        try {
            acc.updateMoney("RUR", -10);
            acc.updateMoney("", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("нормsss");
        }
    }
}
