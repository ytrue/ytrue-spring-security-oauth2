package com.ytrue.security.integration.authenticator;

import com.ytrue.security.integration.IntegrationAuthenticationEntity;

/**
 * 集成认证-认证器抽象类
 */
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {

    /**
     * 预处理
     * @param entity 集成认证实体
     */
    @Override
    public void prepare(IntegrationAuthenticationEntity entity) {

    }

    /**
     *  认证结束后执行
     * @param entity 集成认证实体
     */
    @Override
    public void complete(IntegrationAuthenticationEntity entity) {

    }
}
