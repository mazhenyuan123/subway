package com.xy.subway.mapper;

import com.xy.subway.bean.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DataMapper {
    Data selectNewDataById(int id);
    int insertDataList(List<Data> list,String time);
    List<Data> selectAllDataByCid(int cid);
}
