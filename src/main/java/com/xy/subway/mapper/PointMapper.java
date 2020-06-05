package com.xy.subway.mapper;

import com.xy.subway.bean.Data;
import com.xy.subway.bean.Point;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {
    List<Point> selectAllPoint();
    List<Point> selectPointByName(String name);
    Point selectPointById(int id);
}
