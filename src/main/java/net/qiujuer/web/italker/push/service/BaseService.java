package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Description: 基本上下文入口服务
 * Crete by Anding on 2019-12-05
 */
public class BaseService {

    // 添加一个上下文注解，该注解会给securityContent赋值
    // 具体的值为我们的拦截器中所返回的securityContent
    @Context
    protected SecurityContext securityContext;

    protected User getSelf() {
        return (User) securityContext.getUserPrincipal();
    }
}
