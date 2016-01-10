package moneycalculator.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import static moneycalculator.persistence.DatabaseReader.compareCurrencyIntegrity;

public class ShowNewCurrencyDialog implements CurrencyDialog {

    private String acronym, name, symbol;

    @Override
    public Currency get() {
        System.out.println("¿A que divisa quieres cambiar tu moneda?(Introduzca uno a uno los siguientes datos: acronimo, Nombre, símbolo)");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            acronym = CheckCurrencies.checkAcronym(buffer.readLine());
            name = CheckCurrencies.checkName(buffer.readLine());
            symbol = CheckCurrencies.checkSymbol(buffer.readLine());
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ShowNewCurrencyDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        Currency toChange = new Currency(acronym, name, symbol);

        try {
            if (!compareCurrencyIntegrity(toChange)) {
                throw new IntegrityException();
            }
        } catch (SQLException | ClassNotFoundException | IntegrityException ex) {
            Logger.getLogger(ShowNewCurrencyDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toChange;
    }

}
