package com.cj.dynamicdatasourcedemo.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户实体类
 * @author: linmw
 * @date: 2019年8月12日 上午11:20:41
 */
@Entity
@Table(name = "T_USER")
@DynamicUpdate
@DynamicInsert
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** 用户主键ID */
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "system-uuid")
    private String userId;
    
    /** 用户帐号 */
    @Column(name = "NAME")
    private String name;
    
    /** 用户密码 */
    @Column(name = "PASSWORD")
    private String password;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
