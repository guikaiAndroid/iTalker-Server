package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.user.UpdateInfoModel;
import net.qiujuer.web.italker.push.bean.card.UserCard;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Description: 处理用户信息的Service
 * Crete by Anding on 2019-12-05
 */

@Path("/user")
public class UserService extends BaseService {

    // 用户更新个人信息接口
    // 返回自己的个人信息
    @PUT
    // @Path("") 不需要写  默认就是当前目录
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> update(UpdateInfoModel model) {
        if (!UpdateInfoModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        User self = getSelf();
        self = model.updateToUser(self);
        // 数据库更新
        self = UserFactory.update(self);
        // 架构自己的用户信息
        UserCard card = new UserCard(self, true);

        // 返回
        return ResponseModel.buildOk(card);
    }
}
