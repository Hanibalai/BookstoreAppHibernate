package hibernate.test.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static int getBookAmount() {
        System.out.println("Please enter book amount");
        return (int) getNumber(getString());
    }

    static float getBookPrice() {
        System.out.println("Please enter book price");
        return getFloatNumber(getString());
    }

    static String updateBookData(String data) {
        System.out.printf("Enter a new %s for the book. (Leave blank to stay the %s unchanged)\n", data, data);
        return getString();
    }

    static float updateBookPrice() {
        System.out.println("Enter a new price for the book. (Leave blank to stay the price unchanged)");
        String price = getString();
        if (price != null && price.isEmpty()) return -1;
        else return getFloatNumber(price);
    }

    static int updateBookAmount() {
        System.out.println("Enter a new amount for the book. (Leave blank to stay the price unchanged)");
        String amount = getString();
        if (amount != null && amount.isEmpty()) return -1;
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

    private static long getNumber(String stringNumber) {
        try {
            return Long.parseLong(stringNumber);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input\nTry again");
            return getNumber(getString());
        }
    }

    private static float getFloatNumber(String stringNumber) {
        try {
            return Float.parseFloat(stringNumber);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input\nTry again");
            return getFloatNumber(getString());
        }
    }

    private static String getString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("IO exception.\n" + e.getMessage());
        }
        return null;
    }

    static void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("IO exception.\n" + e.getMessage());
        }
    }
}
