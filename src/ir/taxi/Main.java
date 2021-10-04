package ir.taxi;

import ir.taxi.dataAccess.DriverDataAccess;
import ir.taxi.enumeration.MainMenu;
import ir.taxi.model.Driver;
import ir.taxi.model.Taxi;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Taxi taxi = new Taxi();

    public static void main(String[] args) throws SQLException {
        MainMenu.showMainMenu();
        String choice;
        do {
            choice = scanner.next();
        } while (ValidationUtil.isNumeric(choice));

        int choiceNumber = Integer.parseInt(choice);

        switch (choiceNumber) {
            case 1:
                addGroupOfDrivers();
                break;
            case 2:

        }
    }

    private static void addGroupOfDrivers() throws SQLException {
        String numberOfDrivers;
        do {
            System.out.println("Enter number of drivers:");
            numberOfDrivers = scanner.next();
        } while (ValidationUtil.isNumeric(numberOfDrivers));
        int driverNumbers = Integer.parseInt(numberOfDrivers);
        List<Driver> drivers = new ArrayList<Driver>();
        for (int i = 0; i < driverNumbers; i++) {
            String driverName = getName();
            String driverFamily = getFamily();
            String username = getUsername();
            int phoneNumber = getPhoneNumber();
            int nationalCode = getNationalCode();
            Date birthDate = getDate();
            String plaque = getCarPlateNumber();
            Driver driver = taxi.addDriver(driverName, driverFamily, username, phoneNumber, nationalCode, birthDate, plaque);
            drivers.add(driver);
        }
        if(drivers.size() == driverNumbers){
            DriverDataAccess driverDao = null;
            driverDao.saveGroupOfDrivers(drivers);
            System.out.println("New drivers saved successfully.");
        }
    }

    private static String getCarPlateNumber() {
        String plaque;
        do {
            System.out.println("Enter driver car plate number:");
            plaque = scanner.next();
        } while (ValidationUtil.isIranianCarPlateNumber(plaque));
    }

    private static Date getDate() {
        String date;
        do {
            System.out.println("Enter driver birth date:");
            date = scanner.next();
        } while (ValidationUtil.isPersianDate(date));
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date birthDate = null;
        try {
            birthDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthDate;
    }

    private static int getNationalCode() {
        String nationalCode;
        do {
            System.out.println("Enter national code:");
            nationalCode = scanner.next();
        } while (ValidationUtil.isIranianNationalCode(nationalCode));
        return Integer.parseInt(nationalCode);
    }

    private static int getPhoneNumber() {
        String phoneNumber;
        do {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.next();
        } while (ValidationUtil.isValidPhoneNumber(phoneNumber));
        return Integer.parseInt(phoneNumber);
    }

    private static String getUsername() {
        String username;
        do {
            System.out.println("Enter username:");
            username = scanner.next();
        } while (ValidationUtil.isValidUsername(username));
        return username;
    }

    private static String getFamily() {
        String family;
        do {
            System.out.println("Enter family:");
            family = scanner.next();
        } while (ValidationUtil.isAlphabetic(family));
        return family;
    }

    private static String getName() {
        String name;
        do {
            System.out.println("Enter name:");
            name = scanner.next();
        } while (ValidationUtil.isAlphabetic(name));
        return name;
    }

}
