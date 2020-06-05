package com.xy.subway.mapper;

import com.xy.subway.bean.Sort;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SortMapper {
    Sort selectSortById(int id);
}
