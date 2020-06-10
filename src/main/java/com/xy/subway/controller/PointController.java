package com.xy.subway.controller;

import com.alibaba.excel.EasyExcel;
import com.xy.subway.bean.Data;
import com.xy.subway.bean.Point;
import com.xy.subway.listener.DataListener;
import com.xy.subway.mapper.DataMapper;
import com.xy.subway.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;
    DataMapper dataMapper;
    @PostMapping("/import")
    public String importExcel (@RequestParam(value = "file")MultipartFile file, Date date)throws Exception{
        return pointService.importExcel(file,date);
    }
    @PostMapping("/selectAllPoint")
    public List<Point> selectAllPoint (){
        return pointService.selectAllPoint();
    }
    @PostMapping("/selectById")
    public Point selectById(@RequestParam int id){
        return pointService.selectById(id);
    }
    @GetMapping("/selectHistoryById")
    public List<Data> selectHistoryById(@RequestParam int id){
        return pointService.selectHistoryById(id);
    }
}
