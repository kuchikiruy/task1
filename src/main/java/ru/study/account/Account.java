package ru.study.account;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

interface Action {
    void make();
}

public class Account {
    private String name;
    private Map<Currency, Double> money = new HashMap<>();
    private final Deque<Action> undoStack = new ArrayDeque<>();


    public Account(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {

            undoStack.push(() -> Account.this.name = this.name);

            this.name = name;
        } else throw new IllegalArgumentException("Пустое имя");
    }

    public Map<Currency, Double> getMoney() {
        return new HashMap<>(money);
    }

    public double getMoney(Currency currency) {
        return money.get(currency);
    }

    public void updateMoney(Currency currency, double value) {
        if (currency != null) {
            if (value >= 0) {
                if (money.get(currency) == null) {
                    undoStack.push(() -> Account.this.money.remove(currency));
                } else {
                    undoStack.push(() -> Account.this.money.put(currency, this.money.get(currency)));
                }

                this.money.put(currency, value);
            } else throw new IllegalArgumentException("Валюта не должна быть отрицательной");
        } else throw new IllegalArgumentException("Не указана валюта счета");

    }

    public void undo() {
        if (undoStack.isEmpty()) {
            throw new IllegalStateException("Не возможно отменить");
        }
        undoStack.pop().make();
    }

    public SaveImpl save() {
        return new SaveImpl();
    }

    public enum Currency {
        RUR, EUR, USD;
    }


    interface Save {
        public void load();
    }
    public class SaveImpl implements Save{
        private  String name = Account.this.name;
        private  Map<Account.Currency, Double> money = new HashMap<>(Account.this.getMoney());

        @Override
        public void load() {
            Account.this.name = name;
            Account.this.money = new HashMap<>(money);
        }

    }

}