package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.api.message.MessageCreateModel;
import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.Message;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;

/**
 * Description: 消息数据存储的类
 * Crete by Anding on 2020-04-20
 */
public class MessageFactory {
    // 查询某一个消息
    public static Message findById(String id) {
        return Hib.query(session -> session.get(Message.class, id));
    }

    // 添加一条普通的消息
    public static Message add(User sender, User receiver, MessageCreateModel model) {
        Message message = new Message(sender,receiver,model);
        return save(message);
    }

    // 添加一条群消息
    public static Message add(User sender, Group group, MessageCreateModel model) {
        Message message = new Message(sender,group,model);
        return save(message);
    }

    private static Message save(Message message) {
        return Hib.query(session -> {
            session.save(message);

            // 写到数据库
            session.flush();

            // 紧接着从数据区中查出
            session.refresh(message);
            return message;
        });
    }

}
