package hibernate.test.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

class Console {
    private static final BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    static String getName(String entity) {
        switch (entity) {
            case "author" -> System.out.println("Please enter author's name");
            case "book" -> System.out.println("Please enter book title");
            case "genre" -> System.out.println("Please enter genre name");
        }
        return getString();
    }

    static long getId(String entity) {
        switch (entity) {
            case "author" -> System.out.println("Please enter the author's ID number");
            case "book" -> System.out.println("Please enter the book ID number");
            case "genre" -> System.out.println("Please enter the genre ID number");
        }
        return getNumber(getString());
    }

    static int getBookQuantity() {
        System.out.println("Please enter book quantity");
        return (int) getNumber(getString());
    }

    static BigDecimal getBookPrice() {
        System.out.println("Please enter book price");
        return getDecimal(getString());
    }

    static String updateBookData(String data) {
        System.out.printf("Enter a new %s for the book. (Leave blank to stay the %s unchanged)\n", data, data);
        return getString();
    }

    static BigDecimal updateBookPrice() {
        System.out.println("Enter a new price for the book. (Leave blank to stay the price unchanged)");
        String price = getString();
        if (price.isEmpty()) return new BigDecimal(-1);
        else return getDecimal(price);
    }

    static int updateBookQuantity() {
        System.out.println("Enter a new quantity for the book. (Leave blank to stay the quantity unchanged)");
        String amount = getString();
        if (amount.isEmpty()) return -1;
        else return (int) getNumber(amount);
    }

    static int getOption() {
        return (int) getNumber(getString());
    }

    static void showResult(Object o) {
        if (o != null) {
            System.out.println("Result:");
            System.out.println(o);
        } else System.out.println("Empty result");
    }

    static void showResultList(List<?> list) {
        if (list != null && !list.isEmpty()) {
            System.out.println("Result:");
            for (Object o : list) System.out.println(o);
        } else System.out.println("Empty result");
    }

    private static long getNumber(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input\nTry again");
            return getNumber(getString());
        }
    }

    private static BigDecimal getDecimal(String decimal) {
        try {
            return new BigDecimal(decimal);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input\nTry again");
            return getDecimal(getString());
        }
    }

    private static String getString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("IO exception.\n" + e.getMessage());
        }
        return "";
    }

    static void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("IO exception.\n" + e.getMessage());
        }
    }
}
