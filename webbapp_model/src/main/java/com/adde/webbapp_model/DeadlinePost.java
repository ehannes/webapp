package com.adde.webbapp_model;

import java.util.Date;

public class DeadlinePost extends Post {
    private Date deadline;
    private User responsibleUser;
    private int priority;
    
    /**
     * @param author Author
     * @param responsibleUser Responsible user
     * @param msg Message
     * @param deadline Deadline
     * @param priority Priority between 1 and 5
     */
    public DeadlinePost(User author, User responsibleUser, String msg,
            Date deadline, int priority){
        super(author, msg);
        this.responsibleUser = responsibleUser;
        this.deadline = deadline;
        if(priority < 1){
            System.out.println("DeadlinePost: requested priority < 1, set to 1");
            this.priority = 1;
        } else if(priority > 5){
            System.out.println("DeadlinePost: requested priority > 5, set to 5");
            this.priority = 5;
        } else{
            this.priority = priority;
        }
    }
    
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public int getPriority() {
        return priority;
    }
}
