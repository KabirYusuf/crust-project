package org.crustproject.usermanagement.service;

import org.crustproject.usermanagement.data.model.OauthUserDetail;
import org.crustproject.usermanagement.data.repository.OauthUserDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class OauthUserDetailService {
    private final OauthUserDetailRepository oauthUserDetailRepository;

    public  OauthUserDetailService(OauthUserDetailRepository oauthUserDetailRepository){
        this.oauthUserDetailRepository = oauthUserDetailRepository;
    }
    public void saveOauthUser(OauthUserDetail oauthUserDetail){
        oauthUserDetailRepository.save(oauthUserDetail);
    }

    public boolean findOauthUserByEmailAndType(String email, String type){
        return oauthUserDetailRepository
                .findOauthUserDetailByEmailIgnoreCaseAndOauth2TypeIgnoreCase(email, type)
                .isPresent();
    }
}
