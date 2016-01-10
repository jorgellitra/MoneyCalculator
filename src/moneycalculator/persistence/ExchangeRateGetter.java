package moneycalculator.persistence;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public class ExchangeRateGetter implements ExchangeRateReader {

    @Override
    public ExchangeRate get(Currency from, Currency to) {
        double exchangeRate = 0.0;
        try {
            exchangeRate = DatabaseReader.getExchangeRate(from,to);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ExchangeRateGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ExchangeRate(from, to, exchangeRate);
    }

}
