package server.Spring.enumeration;

public enum UserRole {

    USER("user"),
    ADMIN("admin");

    private final String UserRole;


    UserRole(String userRole) {
        UserRole = userRole;
    }

    public String getUserRole() {
        return UserRole;
    }
}
