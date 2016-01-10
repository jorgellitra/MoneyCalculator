package moneycalculator.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import static moneycalculator.persistence.DatabaseReader.compareCurrencyIntegrity;

public class ShowMoneyToChangeDialog implements MoneyDialog {

    private int valor;
    private String acronym, name, symbol;
    private Currency divisa;

    @Override
    public Money get() {
        System.out.println("¿Qué moneda desea cambiar? (Introduzca uno a uno los siguientes datos: valor, acronimo, Nombre, símbolo)");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            valor = checkValue(Integer.parseInt(buffer.readLine()), buffer);
            acronym = CheckCurrencies.checkAcronym(buffer.readLine());
            name = CheckCurrencies.checkName(buffer.readLine());
            symbol = CheckCurrencies.checkSymbol(buffer.readLine());
            divisa = new Currency(acronym, name, symbol);
            if(!compareCurrencyIntegrity(divisa)) throw new IntegrityException();

        } catch (IOException | SQLException | ClassNotFoundException | IntegrityException ex) {
            Logger.getLogger(ShowMoneyToChangeDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Money(valor, divisa);
    }

    private int checkValue(int valor, BufferedReader money) throws IOException {
        if (valor <= 0) {
            System.out.println("Por favor introduzca un valor positivo");
            int valor2 = Integer.parseInt(money.readLine());
            valor = checkValue(valor2, money);
        }
        return valor;
    }
}
