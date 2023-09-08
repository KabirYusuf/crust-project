package org.crustproject.auth.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.crustproject.auth.AuthException;
import org.crustproject.auth.JwtService;
import org.crustproject.auth.SecuredUser;
import org.crustproject.usermanagement.controller.OauthUserDetailController;
import org.crustproject.usermanagement.data.enums.Role;
import org.crustproject.usermanagement.data.model.OauthUserDetail;
import org.crustproject.usermanagement.data.model.User;
import org.crustproject.usermanagement.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.crustproject.usermanagement.data.enums.Role.USER;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;
    private final OauthUserDetailController oauthUserDetailController;
    private final JwtService jwtService;

    public CustomAuthenticationSuccessHandler(
            UserService userService,
            OauthUserDetailController oauthUserDetailController,
            JwtService jwtService) {
        this.userService = userService;
        this.oauthUserDetailController = oauthUserDetailController;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String name = (String) oauth2User.getAttribute("name");
        String username = (String) oauth2User.getAttribute("login");
        String url = (String) oauth2User.getAttribute("url");
        String email = ((OAuth2User) authentication.getPrincipal()).getName();

        User user = new User();
        user.setEmail(email);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(USER);
        user.setRoles(roleSet);
        String token = jwtService.generateToken(new SecuredUser(user));

        if (userService.findUserByEmail(email) == null)userService.saveUser(user);


        OauthUserDetail oauthUserDetail = new OauthUserDetail();
        oauthUserDetail.setEmail(email);
        oauthUserDetail.setName(name);
        oauthUserDetail.setUsername(username);
        assert url != null;
        if (url.contains("github"))oauthUserDetail.setOauth2Type("github");
        else oauthUserDetail.setOauth2Type("google");

        if (!oauthUserDetailController.findOauthUserByEmailAndType(email, oauthUserDetail.getOauth2Type())){
            oauthUserDetailController.saveOauthUserDetail(oauthUserDetail);
        }

        response.setContentType("application/json");

        String apiResponse = "{\"data\": \"" + token + "," +"\"\n \"isSuccessful\": \" + true}";
        response.getWriter().write(apiResponse);

        log.info(apiResponse);

        try {
            super.onAuthenticationSuccess(request, response, authentication);
        } catch (ServletException e) {
            throw new AuthException(e.getMessage());
        }
    }
}

