package org.primejava.cms.notice;

import org.apache.log4j.Logger;
import org.primejava.cms.pojo.EmailForward;
import org.primejava.cms.pojo.EmailForwardPojo;

public class MailManager {

    private static Logger logger = Logger.getLogger(MailManager.class);
    private volatile static MailManager manager       = null;

    /**
     * Creates a new instance of MailManager.
     */
    private MailManager() {
    }

    /**
     * 这里用一句话描述这个方法的作用. <br/>
     * @author xs
     */
    public static MailManager getInstance() {
        if (null == manager) {
            synchronized (MailManager.class) {
                if (null == manager) {
                    manager = new MailManager();
                }
            }
        }
        return manager;
    }

    public synchronized void sendMailToUser(EmailForwardPojo emailForward){
        if(null==emailForward){
            logger.info("build email forward faild!");
            return;
        }
        if(emailForward.getDestinations().isEmpty()){
            logger.info("email reveice is empty!");
            return;
        }
        EmailForward email = new EmailForward();
        email.send(emailForward);
    }
    
    
    
}
