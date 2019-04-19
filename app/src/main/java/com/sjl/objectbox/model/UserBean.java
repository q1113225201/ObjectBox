package com.sjl.objectbox.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * UserBean
 *
 * @author 沈建林
 * @date 2019/4/19
 */
@Entity
public class UserBean {
    @Id(assignable = true)
    public long id;
    public String username;
}
