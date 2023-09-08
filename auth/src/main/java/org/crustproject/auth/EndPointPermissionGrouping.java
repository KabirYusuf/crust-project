package org.crustproject.auth;

public class EndPointPermissionGrouping {
    public static String [] noAuth(){
        return new String[]{
                "/api/v1/auth/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/webjars/**",
        };
    }

    public static String [] userPermittedEndPoints(){
        return new String[]{
                "/api/v1/tasks/**",

        };
    }
}
