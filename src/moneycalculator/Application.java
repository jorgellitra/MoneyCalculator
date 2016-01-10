package moneycalculator;

import moneycalculator.control.ExchangeCommand;
import moneycalculator.persistence.ExchangeRateGetter;
import moneycalculator.ui.ShowNewCurrencyDialog;
import moneycalculator.ui.ShowMoneyToChangeDialog;
import moneycalculator.ui.ShowMoneyDisplay;

public class Application {

    public static void main(String[] args) {
        while (true){
            ExchangeCommand exchangeCommand = new ExchangeCommand(
                    new ShowMoneyToChangeDialog(),
                    new ShowNewCurrencyDialog(),
                    new ExchangeRateGetter(), 
                    new ShowMoneyDisplay()
            );
            exchangeCommand.execute();       
        }
    }
    
}
