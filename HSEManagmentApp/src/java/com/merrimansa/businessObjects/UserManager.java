/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.ejb.UserFacade;
import com.merrimansa.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
public class UserManager {

    @Inject
    UserFacade userFacade;

    public UserManager() {

    }

    /**
     * Returns a List of active users
     *
     * @return
     */
    public List<UserVO> getActiveUsers() {
        List<UserVO> users = new ArrayList();
        //Search for active users and add them to list of UserVOs
        for (User u : userFacade.findAll()) {
            if (u.getActive()) {
                users.add(new UserVO(u));
                System.out.println("User "+u.getSurname());
            }
        }

        return users;
    }

    /**
     * Returns a list of active users that hold the role identified by the param
     *
     * @param Role
     * @return
     */
    public List<UserVO> getActiveUsers(String Role) {
        List<UserVO> users = this.getActiveUsers();
        List<UserVO> usersOut = new ArrayList();
        for (UserVO u : users) {
            if (u.hasRole(Role)) {
                usersOut.add(u);
            }

        }
        return usersOut;
    }

}
