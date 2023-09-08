package org.crustproject.usermanagement.data.repository;

import org.crustproject.usermanagement.data.model.OauthUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthUserDetailRepository extends JpaRepository<OauthUserDetail, Long> {
    Optional<OauthUserDetail> findOauthUserDetailByEmailIgnoreCaseAndOauth2TypeIgnoreCase(String email, String type);
}
