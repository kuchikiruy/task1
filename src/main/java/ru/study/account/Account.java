package ru.study.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Account extends Command{
    private String name;
    private final Map<String, Double> money = new HashMap<>();
    private final Stack<Account> undoStack = new Stack<>();

    public Account(String name) {
        setName(name);
        execute();
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
            execute();
        } else throw new IllegalArgumentException("Пустое имя");
    }

    public Map<String, Double> getMoney() {
        return money;
    }

    public double getMoney(String currency) {
        return money.get(currency);
    }

    public void updateMoney(String currency, double value) {
        if (currency != null && !currency.isEmpty()) {
            if (value >= 0) {
                this.money.put(currency, value);
                execute();
            } else throw new IllegalArgumentException("Валюта не должна быть отрицательной");
        } else throw new IllegalArgumentException("Не указана валюта счета");

    }


    @Override
    public void undo() {
        if (!undoStack.isEmpty()) {throw new IllegalStateException("Не возможно отменить");}
        this.name = undoStack.lastElement().name;
        this.money.putAll(undoStack.lastElement().money);
        undoStack.removeElementAt(undoStack.size()-1);
    }

    @Override
    public void execute() {
        undoStack.push(this);
    }

    @Override
    public void save() {

    }

    @Override
    public Object load() {
        return null;
    }
}


abstract class Command {

    public abstract void execute();

    public abstract void undo();

    public abstract void save();
    public abstract Object load();
}