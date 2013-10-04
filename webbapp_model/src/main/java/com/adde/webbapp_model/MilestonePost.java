/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import java.util.Date;
import java.util.List;

public class MilestonePost extends DeadlinePost {
    List<User> assignedTo;
    
    /**
     * @param author Author
     * @param responsibleUser Responsible user
     * @param msg Message
     * @param deadline Deadline
     * @param priority Priority between 1 and 5
     * @param assignedTo Users the milestone is assigned to
     */
    public MilestonePost(User author, User responsibleUser, String msg,
            Date deadline, int priority, List<User> assignedTo){
        super(author, responsibleUser, msg, deadline, priority);
    }
    
    public List<User> getAssignedTo(){
        return assignedTo;
    }
}
