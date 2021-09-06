package com.epam.jwd.view;

public class Menu {

    private static final String START_MENU = """
            1. Sign In
            2. Registration
            """;
    private static final String MAIN_MENU = """
            3. Buy Ticket
            4. Check balance
            5. Get Ticket Price
            6. Change User Name
            7. Change User age
            8. Change User email
            9. Get Ticket List
            10. Sign Out
            """;
    private static final String CHOOSE_FUNCTION = "Choose function you want";
    private static final String INPUT_USER_NAME = "Please input your userName";
    private static final String INPUT_USER_BALANCE = "Please input your balance";
    private static final String INPUT_USER_AGE = "Please input your age";
    private static final String INPUT_USER_EMAIL = "Please input your EMAIL";
    private static final String INPUT_MOVIE_NAME = "Enter movie Name(Titanic, Lolita, Pregnancy test, The Dark Knight, Hatred, Brother 2," +
            " Alien, Devils, Viy, Free Guy, Dad, Presidents, Midnight in the Switchgrass, Ageev, Lupin)";
    private static final String ERROR_MOVIE_MESSAGE = "There is no such movie now";
    private static final String NO_CASH_MESSAGE = "You haven't got enough cash to buy ticket";
    private static final String USER_BALANCE = "Your balance: ";
    private static final String TICKET_PRICE = "Ticket Price: ";
    private static final String ERROR_NAME = "Invalid name provided";
    private static final String ERROR_AGE = "Invalid age provided";
    private static final String ERROR_EMAIL = "Invalid email provided";
    private static final String AVAILABLE_FOR_KIDS_TICKETS = "Available for kids tickets: ";

    public static void printMenu() {
        System.out.println(START_MENU);
    }

    public static void printFunctions() {
        System.out.println(MAIN_MENU);
    }

    public static void printChoose(){
        System.out.println(CHOOSE_FUNCTION);
    }

    public static void printInputUserName() {
        System.out.println(INPUT_USER_NAME);
    }

    public static void printInputUserBalance() {
        System.out.println(INPUT_USER_BALANCE);
    }

    public static void printInputUserAge() {
        System.out.println(INPUT_USER_AGE);
    }

    public static void printInputUserEMAIL() {
        System.out.println(INPUT_USER_EMAIL);
    }

    public static void printInputMovieName() {
        System.out.println(INPUT_MOVIE_NAME);
    }

    public static void printErrorMovieMessage() {
        System.out.println(ERROR_MOVIE_MESSAGE);
    }

    public static void printNoCash() {
        System.out.println(NO_CASH_MESSAGE);
    }

    public static void printUserBalance(double balance) {
        System.out.println(USER_BALANCE + balance);
    }

    public static void printTicketPrice(double price) {
        System.out.println(TICKET_PRICE + price);
    }

    public  static void printErrorName() {
        System.out.println(ERROR_NAME);
    }

    public  static void printErrorAge() {
        System.out.println(ERROR_AGE);
    }

    public  static void printErrorEmail() {
        System.out.println(ERROR_EMAIL);
    }

    public static void printAvailableForKidsTickets() {
        System.out.println(AVAILABLE_FOR_KIDS_TICKETS);
    }
}
