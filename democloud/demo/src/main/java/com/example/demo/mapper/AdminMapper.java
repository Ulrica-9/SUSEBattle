package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @项目名: democloud
 * @文件名: AdminMapper
 * @作者: LuoTianYu
 * @日期:2022/12/8 9:14
 */
@Mapper
interface AdminMapper extends BaseMapper<Admin> {
}
