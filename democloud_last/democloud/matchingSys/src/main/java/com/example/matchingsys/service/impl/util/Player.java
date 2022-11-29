package com.example.matchingsys.service.impl.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @项目名: democloud
 * @文件名: Player
 * @作者: LuoTianYu
 * @日期:2022/11/27 0:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer userId;
    private Integer rating; // 天梯积分
    private Integer botId;
    private Integer waitingTime; // 等待时间
}
