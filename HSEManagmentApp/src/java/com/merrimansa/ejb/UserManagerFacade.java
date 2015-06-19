/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.ejb;

import com.merrimansa.businessObjects.UserManager;
import com.merrimansa.businessObjects.UserVO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Stateless
public class UserManagerFacade {
    
    @Inject
    UserManager userManager;
    
    @RolesAllowed({"HSEManager","Administrator","Assessor"})
    public List<UserVO> getActiveUsers(String Role){
        return userManager.getActiveUsers(Role);
    }
    
    @RolesAllowed({"HSEManager","Administrator","Assessor"})
    public List<UserVO> getActiveUsers(){
        return userManager.getActiveUsers();
    }
    
    public UserVO getUserVoById(int Id){
        return userManager.getUserVoById(Id);
    }
}
