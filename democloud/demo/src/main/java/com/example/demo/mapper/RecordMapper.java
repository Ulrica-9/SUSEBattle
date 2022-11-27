package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

/**
 * @项目名: demo
 * @文件名: RecordMapper
 * @作者: LuoTianYu
 * @日期:2022/11/26 19:29
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
}
