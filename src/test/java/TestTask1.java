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
    @DisplayName("Операции с деняжками")
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

    @Test
    @DisplayName("Галя, отмена")
    public void testUndo() {
        try {
            acc.undo();
            acc.undo();
            acc.undo();
            acc.undo();
        } catch (IllegalStateException e) {System.out.println("норм");}

    }

    @Test
    @DisplayName("Спаси и сохрани")

    public void testSave(){
        acc.save();
        acc.load();
    }
}
