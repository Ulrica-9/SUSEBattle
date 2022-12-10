package com.example.demo.service.admin;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: AdminService
 * @作者: LuoTianYu
 * @日期:2022/12/8 9:26
 */
public interface AdminService {

    public Map<String,String> LoginAdmin(String name,String password);
}
