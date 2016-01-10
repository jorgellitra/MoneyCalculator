package moneycalculator.ui;

import moneycalculator.model.Money;

public class ShowMoneyDisplay implements MoneyDisplay {

    @Override
    public void show(Money money) {
        System.out.println(money.getAmount() + " " + money.getCurrency().getAcronym());
    }

}
