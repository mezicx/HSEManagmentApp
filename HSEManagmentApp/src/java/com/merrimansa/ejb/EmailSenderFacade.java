/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.ejb;

import com.merrimansa.businessObjects.EmailSender;
import javax.ejb.Stateless;

/**
 *
 * @author Steve
 */

@Stateless
public class EmailSenderFacade {

    EmailSender sender;
    
    public EmailSenderFacade(){ 
            
            sender = new EmailSender(465,"smtp.gmail.com", "mezicx@googlemail.com",
            true,"mezicx@googlemail.com","Sm502025","SMTP",true);
    }
    
    public void sendMail(String to, String subject, String body){
        sender.sendMail(to, subject, body);
    }
    
    
    
}
