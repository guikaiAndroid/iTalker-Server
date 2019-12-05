package net.qiujuer.web.italker.push.bean.api.account;

import com.google.gson.annotations.Expose;
import net.qiujuer.web.italker.push.bean.card.UserCard;
import net.qiujuer.web.italker.push.bean.db.User;

/**
 * Description:
 * Crete by Anding on 2019-12-02
 */
public class AccountRspModel {

    // 用户基本信息
    @Expose
    private UserCard user;

    // 当前登录的账号
    @Expose
    private String account;

    // 当前登录成功后获取的Token,
    // 可以通过Token获取用户的所有信息
    @Expose
    private String token;

    // 标示是否已经绑定到了设备PushId
    @Expose
    private boolean isBind;

    public AccountRspModel(User user) {
        // 默认无绑定
        this(user, false);
    }

    public AccountRspModel(User user, boolean isBind) {
        this.user = new UserCard(user);
        this.account = user.getPhone();
        this.token = user.getToken();
        this.isBind = isBind;
    }
}
