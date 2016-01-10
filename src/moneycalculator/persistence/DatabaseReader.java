package moneycalculator.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import moneycalculator.model.Currency;

public class DatabaseReader {

    private static Connection c;
    private static Statement stmt;

    private static void setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:ExchangeRatesDB.db");
        stmt = c.createStatement();
    }

    private static void closeConnection(ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        c.close();
    }

    public static boolean searchIfExists(String column ,String object) throws SQLException, ClassNotFoundException {
        setConnection();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ExchangeRates WHERE "+column+"='" + object + "'");
        
        if (!rs.next()) {
                closeConnection(rs);
                return false;
        }
        closeConnection(rs);
        return true;
    }

    public static double getExchangeRate(Currency from, Currency to) throws SQLException, ClassNotFoundException {
        
        String acronymFrom = from.getAcronym();
        String acronymTo = to.getAcronym();
        
        setConnection();
        ResultSet rs = stmt.executeQuery("SELECT TO"+acronymTo+" FROM ExchangeRates WHERE ACRONYM='" + acronymFrom + "'");
        double result=rs.getDouble("TO"+acronymTo);
        closeConnection(rs);
        return result;
    }
    public static boolean compareCurrencyIntegrity(Currency comparable) throws SQLException, ClassNotFoundException{
        String acronym=comparable.getAcronym();
        String name=comparable.getName();
        String symbol=comparable.getSymbol();
        
        setConnection();
        
        ResultSet rs = stmt.executeQuery("SELECT NAME,SYMBOL FROM ExchangeRates WHERE ACRONYM='" + acronym + "'");
        
        if(!rs.getString("NAME").equals(name) || !rs.getString("SYMBOL").equals(symbol)){
            closeConnection(rs);
            return false;
        }
        closeConnection(rs);
        return true;
    } 
}
