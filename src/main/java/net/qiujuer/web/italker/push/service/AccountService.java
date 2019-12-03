package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.account.LoginModel;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.account.AccountRspModel;
import net.qiujuer.web.italker.push.bean.api.account.RegisterModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Description: 后端入口
 * Crete by Anding on 2019-12-01
 */

@Path("/account")
public class AccountService {

    // 登录接口
    @POST
    @Path("/login")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> login(LoginModel model) {
        if (!LoginModel.check(model)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }

        // 登录逻辑操作 数据库查询
        User user = UserFactory.login(model.getAccount(), model.getPassword());

        if (user != null) {
            //如果有携带PushId
            if (!Strings.isNullOrEmpty(model.getPushId())) {
                // 绑定PushId
            }

            //返回当前的账户
            AccountRspModel rspModel = new AccountRspModel(user);
            return ResponseModel.buildOk(rspModel);
        } else {
            // 登录失败
            return ResponseModel.buildLoginError();
        }
    }

    // 注册接口
    @POST
    @Path("/register")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> register(RegisterModel model) {

        //检查参数
        if (!RegisterModel.check(model)) {
            //返回参数异常
            return ResponseModel.buildParameterError();
        }

        //检测账户已存在
        User user = UserFactory.findByPhone(model.getAccount().trim());
        if (user != null) {
            return ResponseModel.buildHaveAccountError();
        }

        //检测用户名是否已注册过
        user = UserFactory.findByName(model.getName().trim());
        if (user != null) {
            return ResponseModel.buildHaveNameError();
        }

        //开始逻辑注册 数据库
        user = UserFactory.register(model.getAccount(),
                model.getPassword(),
                model.getName());

        if (user != null) {

            // PushId

            //返回当前的账户信息
            AccountRspModel rspModel = new AccountRspModel(user);
            return ResponseModel.buildOk(rspModel);

        } else {
            //注册异常
            return ResponseModel.buildRegisterError();
        }
    }

}
