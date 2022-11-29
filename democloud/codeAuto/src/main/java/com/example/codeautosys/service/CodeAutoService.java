package com.example.codeautosys.service;

/**
 * @项目名: democloud
 * @文件名: CodeAutoService
 * @作者: LuoTianYu
 * @日期:2022/11/28 16:22
 */
public interface CodeAutoService {
    // 用户 输入代码 地图

    String addCode(Integer userId,String botCode,String input);
}
