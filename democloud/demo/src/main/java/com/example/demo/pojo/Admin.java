package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @项目名: democloud
 * @文件名: Admin
 * @作者: LuoTianYu
 * @日期:2022/12/8 9:12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    //    与数据库进行对应的字段
    @TableId(type = IdType.AUTO) // 实现id自增 -> +1
    private Integer id;
    private String admin; // 管理员账户
    private String password; // 密码
}
