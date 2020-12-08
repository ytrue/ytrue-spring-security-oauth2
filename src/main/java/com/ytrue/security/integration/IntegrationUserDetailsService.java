package com.ytrue.security.integration;

import com.ytrue.security.dto.User;
import com.ytrue.security.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 集成认证-用户细节服务
 */
@Service
public class IntegrationUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<IntegrationAuthenticator> authenticators;


    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //去获得上下文
        IntegrationAuthenticationEntity entity = IntegrationAuthenticationContext.get();

        //判断是否为空
        if (entity == null) {
            entity = new IntegrationAuthenticationEntity();
        }

        //判断是否支持集成认证类型
        UserPojo pojo = authenticate(entity);

        //判断一下
        if (pojo == null) {
            throw new OAuth2Exception("此账号不存在！");
        }

        return new User(pojo.getName(), passwordEncoder.encode(entity.getAuthParameter("password")), "123.png",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
    }

    /**
     * 判断是否支持集成认证类型
     * @param entity
     * @return
     */
    private UserPojo authenticate(IntegrationAuthenticationEntity entity) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(entity)) {
                    return authenticator.authenticate(entity);
                }
            }
        }
        throw new OAuth2Exception("无效的auth_type参数值！");
    }
}
