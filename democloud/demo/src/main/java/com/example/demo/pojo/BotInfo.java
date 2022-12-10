package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @项目名: demo
 * @文件名: BotInfo
 * @作者: LuoTianYu
 * @日期:2022/11/12 14:56
 */

// Bot表的映射
@Data
// 自动填充get 和 set
@NoArgsConstructor
// 无参
@AllArgsConstructor
// 有参
public class BotInfo {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;
    private Integer userId; // 驼峰命名对应数据库中下划线命名
    private String name;
    private String description; // 描述
    private String content; // 代码内容
    // 日期类通过注解定义格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone =  "Asia/Shanghai")
    private Date createtime;  // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone =  "Asia/Shanghai")
    private Date modifytime; // 修改时间
    private String language; // 语言
    private String play; // 哪一个游戏
}
