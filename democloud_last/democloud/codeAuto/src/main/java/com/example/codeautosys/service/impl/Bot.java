package com.example.codeautosys.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @项目名: democloud
 * @文件名: Bot
 * @作者: LuoTianYu
 * @日期:2022/11/28 20:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bot {
    Integer userId;
    String botCode;
    String input;

}
