package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @项目名: demo
 * @文件名: Record
 * @作者: LuoTianYu
 * @日期:2022/11/26 19:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
//    数据库中下划线 - 驼峰命名

    //    与数据库进行对应的字段
    @TableId(type = IdType.AUTO) // 实现id自增 -> +1
    private Integer id;
    private Integer aId;
    private Integer aSx;
    private Integer aSy;
    private Integer bId;
    private Integer bSx;
    private Integer bSy;
    private String aSteps;
    private String bSteps;
    private String map;
    private String loser;

    // 日期类通过注解定义格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone =  "Asia/Shanghai")
    private Date createtime;
}
