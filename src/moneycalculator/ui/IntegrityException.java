package moneycalculator.ui;

class IntegrityException extends Exception {

    public IntegrityException() {
        System.out.println("No ha insertado bien la divisa. Ejecute de nuevo");
        System.exit(0);
    }

}
