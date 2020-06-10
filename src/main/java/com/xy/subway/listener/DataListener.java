package com.xy.subway.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.xy.subway.algorithm.SafeState;
import com.xy.subway.bean.Data;
import com.xy.subway.bean.Point;
import com.xy.subway.mapper.DataMapper;
import com.xy.subway.mapper.PointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataListener extends AnalysisEventListener<Data> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataListener.class);

    private static final int BATCH_COUNT = 5;
    List<Data> list = new ArrayList<>();
    private DataMapper dataMapper;
    private PointMapper pointMapper;
    private Date date;
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param
     */
    public DataListener(DataMapper dataMapper,PointMapper pointMapper,Date date) {
        this.dataMapper = dataMapper;
        this.pointMapper = pointMapper;
        this.date = date;
    }
    @Override
    public void invoke(Data data, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            try {
                saveData();
            }catch (Exception e){
                e.printStackTrace();
            }
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
// 这里也要保存数据，确保最后遗留的数据也存储到数据库
        try {
            saveData();
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info("所有数据解析完成！");
    }
    /**
     * 加上存储数据库
     */
    private void saveData() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now;
        if (this.date!=null){
            now = this.date;
        }else {
            now = new Date();
        }
        for(Data data:list){
            System.out.println(data.getCid());
            Point point = pointMapper.selectPointById(data.getCid());
            Data lastValue = dataMapper.selectNewDataById(data.getCid());
            SafeState ss = new SafeState(point.getCvalue(),point.getCrate(),point.getIvalue(),df.parse(point.getIdate()),lastValue.getValue(),df.parse(lastValue.getTime()),data.getValue(),now);
            data.setRate(ss.getRate());
            data.setSafe(ss.getSafe());
        }

        LOGGER.info("{}条数据，开始存储数据库！", list.size());

        dataMapper.insertDataList(list,df.format(now));
        System.out.println(list);
        LOGGER.info("存储数据库成功！");
    }
}
