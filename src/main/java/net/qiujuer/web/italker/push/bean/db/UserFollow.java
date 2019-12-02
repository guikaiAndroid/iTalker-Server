package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Description: 用来描述用户之间关系的Model
 * 用于用户直接进行好友关系的实现 通俗点此表保存此种关系的所有记录
 *
 * Crete by Anding on 2019-11-30
 */

@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;


    // 定义一个发起人，你关注某人(好友)，这里就是你(仅仅用你的id)
    // 多对一 你可以关注很多人 你的每一次关注都是一条记录
    // 你可以创建很多很多个关注的信息，所以是多对一
    // 这里是多对一：一个User 对应多个UserFollow(多个关注记录)
    // optional 不可选，必须存储，一条记录一定包含此发起人id
    @ManyToOne(optional = false)
    // 定义关联的表字段名为origin,对应的是User,id
    // 定义的是数据库中的存储字段
    @JoinColumn(name = "originId")
    private User origin;

    // 把这个列提取到我们的Model中，不允许为null,不允许更新，插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String originId;


    // 定义关注的目标人，你关注的人
    // 也是多对一 你可以被很多人关注，每一次关注都是一条记录
    // 也就是多个 UserFollow 对应一个User的情况
    @ManyToOne(optional = false)
    // 定义关联的表字段名为targetId, 对应的事User.id
    // 定义的事数据库中的存储字段
    @JoinColumn(name = "targetId")
    private User target;

    // 为了高效便捷，可以把这个列提取到我们的Model中，不允许为空，不允许更新，插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String targetId;

    // 别名 备注
    @Column
    private String alias;

    // 定义创建时间戳，在创建的时候已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建时就已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
