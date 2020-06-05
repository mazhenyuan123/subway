package com.xy.subway.service;

import com.alibaba.excel.EasyExcel;
import com.xy.subway.bean.Data;
import com.xy.subway.bean.Point;
import com.xy.subway.bean.Sort;
import com.xy.subway.listener.DataListener;
import com.xy.subway.mapper.DataMapper;
import com.xy.subway.mapper.PointMapper;
import com.xy.subway.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.File;
import java.util.List;

@Service
public class PointService {
    @Autowired
    private PointMapper pointMapper;
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private SortMapper sortMapper;
    public String importExcel(MultipartFile file) throws Exception{
        EasyExcel.read(file.getInputStream(),Data.class,new DataListener(dataMapper,pointMapper)).sheet().doRead();
        return "success";
    }
    public List<Point> selectAllPoint (){
        List<Point> points = pointMapper.selectAllPoint();
        for(Point point:points){
            int id = point.getId();
            int sid = point.getSort().getId();
            Sort sort = sortMapper.selectSortById(sid);
            point.setSort(sort);
            Data data = dataMapper.selectNewDataById(id);
            point.setData(data);
        }
        return points;
    }
    public Point selectById(int id){
        Point point = pointMapper.selectPointById(id);
        int sid = point.getSort().getId();
        Sort sort = sortMapper.selectSortById(sid);
        point.setSort(sort);
        Data data = dataMapper.selectNewDataById(id);
        point.setData(data);
        return point;
    }
    public List<Data> selectHistoryById(int id){
        return dataMapper.selectAllDataByCid(id);
    }
}
