package org.example;

public final class GlobalVariables {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/homework";
    private static final String LOGIN = "igorshmidt";
    private static final String PASSWORD = "Shmidtigorqwerty99";

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getLogin() {
        return LOGIN;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
