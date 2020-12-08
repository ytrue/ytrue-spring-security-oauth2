package com.ytrue.security.integration.authenticator;

import com.ytrue.security.integration.IntegrationAuthenticationEntity;
import com.ytrue.security.integration.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 普通认证器（用户名+密码）
 */
@Component
/**
 * service有多个实现类的时候，注入的时候需要按照@Qualifier() 注入，
 * 加了@Primary 可以实现一个默认的注入类
 */
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 预处理
     *
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public UserPojo authenticate(IntegrationAuthenticationEntity entity) {
        String name = entity.getAuthParameter("name");
        String pwd = entity.getAuthParameter("pwd");
        if (name == null || pwd == null) {
            //这里的异常要定制
            throw new OAuth2Exception("用户名或密码不能为空");
        }
        //UserPojo pojo = mapper.findByName(name);

//        if (passwordEncoder.matches(pwd, pojo.getPwd())) {
//            return pojo;
//        }
//        return null;
        return new UserPojo(2, "usernameAndPassword-yang", "17687410790", "yang@qq.com", "123456");
    }

    /**
     * 结束
     *
     * @param entity 集成认证实体
     * @return
     */
    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return StringUtils.isEmpty(entity.getAuthType());
    }
}
