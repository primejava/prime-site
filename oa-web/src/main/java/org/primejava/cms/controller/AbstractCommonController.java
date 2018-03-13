package org.primejava.cms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AbstractCommonController {
	  /**
     * 发送成功消息.
     * @author ghlin
     */
    public void sendSuccessMsg(HttpServletResponse response) {
        this.sendMsg(response,true, "", "");
    }
    
    public <T> void sendMsg(HttpServletResponse response,boolean isSuccess, T data, String msg) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("success", isSuccess);
        responseMap.put("data", data);
        responseMap.put("message", msg);
        this.sendResponseMsg(response,new Gson().toJson(responseMap));
    }
	  /**
     * 发送数组消息.
     * @author ghlin
     */

    public <E> void sendArrayMsg(HttpServletResponse response,Collection<E> collectObj) {
        this.sendResponseMsg(response,new Gson().toJson(collectObj));
    }
    
	 /**
     * 发送respons消息.
     * @author ghlin
     * @param jsonMsg
     */
    public void sendResponseMsg(HttpServletResponse response, String jsonMsg) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonMsg);
            writer.flush();
        } catch (IOException e) {
        } finally {
            if (null != writer) {
                writer.close();
                writer = null;
            }
        }
    }
}
