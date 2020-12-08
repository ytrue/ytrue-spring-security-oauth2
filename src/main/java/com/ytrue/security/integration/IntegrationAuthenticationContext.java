package com.ytrue.security.integration;

/**
 * 集成认证上下文
 */

public class IntegrationAuthenticationContext {
    private static final ThreadLocal<IntegrationAuthenticationEntity> holder = new ThreadLocal<>();

    public static void set(IntegrationAuthenticationEntity entity) {
        holder.set(entity);
    }

    public static IntegrationAuthenticationEntity get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
