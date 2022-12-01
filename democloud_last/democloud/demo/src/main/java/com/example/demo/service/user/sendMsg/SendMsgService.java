package com.example.demo.service.user.sendMsg;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: SendMsg
 * @作者: LuoTianYu
 * @日期:2022/11/30 21:59
 */
public interface SendMsgService {

    // 获取邮箱
    public Map<String,String> getEmail(String email);
}
