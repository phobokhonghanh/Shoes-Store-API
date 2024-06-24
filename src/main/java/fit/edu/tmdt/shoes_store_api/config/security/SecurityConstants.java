package fit.edu.tmdt.shoes_store_api.config.security;

public interface SecurityConstants {
    String[] ADMIN_API_PATHS = {
            "/api/v1/admin/**"
    };
    String[] USER_API_PATHS = {
            "/api/v1/user-api/account/**",
            "/api/v1/user-api/order/**",
    };
    String[] CLIENT_API_PATHS = {
            "/api/v1/client-api/**",
            "/admin/img/upload/product/**"
    };

    interface Role {
        String ADMIN = "ADMIN";

        String CLIENT = "CLIENT";

    }
}
