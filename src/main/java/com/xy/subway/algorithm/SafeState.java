package com.xy.subway.algorithm;

import java.util.Date;

public class SafeState {
    private static int safe = 0;
    private static double rate = 0;

    //控制值
    private double referenceValue;
    private double referenceRate;
    //初始值
    private double initialValue;
    private Date initialDate;
    //上次测量值
    private double lastValue;
    private Date lastDate;
    //本次测量值
    private double currentValue;
    private Date currentDate;

    public SafeState(double referenceValue,double referenceRate,double initialValue,Date initialDate,double lastValue,Date lastDate,double currentValue,Date currentDate){
        this.referenceValue = referenceValue;
        this.referenceRate = referenceRate;
        this.initialValue = initialValue;
        this.initialDate = initialDate;
        this.lastValue = lastValue;
        this.lastDate = lastDate;
        this.currentValue = currentValue;
        this.currentDate = currentDate;
        setRate();
        setSafe();
    }
    private void setRate(){
        long diff = currentDate.getTime() - lastDate.getTime();
        long days = diff / (1000*60*60*24);
        rate = (currentValue-lastValue)/days;
    }
    private void setSafe(){
        double cumulativeValue = Math.abs(currentValue - initialValue);
        double absRate = Math.abs(rate);

        if((cumulativeValue>=referenceValue) && (absRate>=referenceRate)){
            safe = 3;
        }else if ((((referenceValue>cumulativeValue)&&(0.85*referenceValue<=cumulativeValue))&&((referenceRate>absRate)&&(0.85*referenceRate<=absRate)))
                ||((referenceValue<=cumulativeValue)&&(referenceRate>absRate))
                ||((referenceValue>cumulativeValue)&&(referenceRate<=absRate))
               ){
            safe = 2;
        }else if((((0.85*referenceValue>cumulativeValue)&&(0.70*referenceValue<=cumulativeValue))&&((0.85*referenceRate>absRate)&&(0.70*referenceRate<=absRate)))
                ||(((referenceValue>cumulativeValue)&&(0.85*referenceValue<=cumulativeValue))&&(0.85*referenceRate>absRate))
                ||((0.85*referenceValue>cumulativeValue)&&((referenceRate>absRate)&&(0.85*referenceRate<=absRate)))){
            safe = 1;
        }else{
            safe = 0;
        }
    }

    public int getSafe (){
        return safe;
    }
    public double getRate(){
        return rate;
    }
}
