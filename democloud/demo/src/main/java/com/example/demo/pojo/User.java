package com.example.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
// 自动填充get set等
@NoArgsConstructor
// 无参构造
@AllArgsConstructor
// 有参构造
public class User {
    //    与数据库进行对应的字段
    @TableId(type = IdType.AUTO) // 实现id自增 -> +1
    private Integer id;
    private String username; // 姓名
    private String password; // 密码
    private String photo; // 头像
    private Integer rating; // 排位分数
//     大学
//    性别
//    联系方式
//    个人简介
}
