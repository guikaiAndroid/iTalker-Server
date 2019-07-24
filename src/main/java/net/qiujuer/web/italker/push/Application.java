package net.qiujuer.web.italker.push;

import net.qiujuer.web.italker.push.service.AccountService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application() {

        //注册逻辑处理的包名
        packages(AccountService.class.getPackage().getName());

        //注册Json解析器
        register(JacksonJsonProvider.class);

        //注册日志打印输出
        register(Logger.class);
    }

}
