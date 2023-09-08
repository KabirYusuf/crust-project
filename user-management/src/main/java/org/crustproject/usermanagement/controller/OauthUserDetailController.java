package org.crustproject.usermanagement.controller;

import org.crustproject.usermanagement.data.model.OauthUserDetail;
import org.crustproject.usermanagement.service.OauthUserDetailService;
import org.springframework.stereotype.Component;

@Component
public class OauthUserDetailController {
    private final OauthUserDetailService oauthUserDetailService;

    public OauthUserDetailController(OauthUserDetailService oauthUserDetailService){
        this.oauthUserDetailService = oauthUserDetailService;
    }

    public void saveOauthUserDetail(OauthUserDetail oauthUserDetail){
        oauthUserDetailService.saveOauthUser(oauthUserDetail);
    }

    public boolean findOauthUserByEmailAndType(String email, String type){
        return oauthUserDetailService.findOauthUserByEmailAndType(email, type);
    }
}
