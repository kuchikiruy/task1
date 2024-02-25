package ru.study.account;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Account {
    private String name;
    private final Map<String, Double> money = new HashMap<>();

    public Account(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else throw new IllegalArgumentException("Пустое имя");
    }

    public Map<String, Double> getMoney() {
        return money;
    }
    public double getMoney( String currency) {
        return money.get(currency);
    }

    public void updateMoney (String currency, double value)   {
        if (currency != null && !currency.isEmpty()){
            if(value >= 0) {
                this.money.put(currency, value);
            } else throw new IllegalArgumentException("Валюта не должна быть отрицательной");
        } else throw new IllegalArgumentException("Не указана ваолюта счета");

    }


}
