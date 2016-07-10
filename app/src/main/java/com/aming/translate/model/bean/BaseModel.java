package com.aming.translate.model.bean;

import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.aming.translate.db.TranslateRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wenming on 2016/6/26.
 */
public class BaseModel {
    public int errorCode;
    public String query;
    public ArrayList<String> translation;
    public Basic basic;
    public ArrayList<Web> web;

    private String checkErrorCode(){
        StringBuilder builder = new StringBuilder();
        switch (errorCode){
            case 0:
                builder.append("翻译:\r\r\r");
                String curTranslation = getTranslation();
                String explain = "";
                builder.append(curTranslation);
                builder.append("\n");
                builder.append("音标:\r\r\r");
                if (basic==null) {
                    break;
                }
                builder.append(basic.phonetic);
                builder.append("\n");
                builder.append("解释:\r\r\r");
                explain = basic.toString();
                builder.append(explain);
                builder.append("\n\n");
                builder.append("网络释义:");
                builder.append("\n");
                if (web!=null){
                    String webTranslation = getWebTranslation();
                    builder.append(webTranslation);
                    saveToDb(curTranslation,explain,webTranslation);
                }
                break;
            case 20:
                builder.append("要翻译的文本过长");
                break;
            case 30:
                builder.append("无法进行有效的翻译");
                break;
            case 40:
                builder.append("不支持的语言类型");
                break;
            case 50:
                builder.append("无效的key");
                break;
            case 60:
                builder.append("无词典结果，仅在获取词典结果生效");
                break;

        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return checkErrorCode();
    }

    public String getTranslation(){
        String string = "";
        for (int i = 0; i < translation.size(); i++) {
            String tran = translation.get(i);
            string = string + tran;
            if (i != translation.size()-1){
                string = string + ",";
            }
        }
        return string;
    }

    public String getWebTranslation(){
        String string = "";
        for (int i = 0; i < web.size(); i++) {
            Web w = web.get(i);
            string = string + w.toString();
        }
        return string;
    }

    public void saveToDb(String translation,String explain,String webTranslation){
        TranslateRecord translateRecord = new TranslateRecord();
        translateRecord.setPhonetic(basic.phonetic);
        translateRecord.setTranslation(translation);
        translateRecord.setQuery(query);
        translateRecord.setWebTranslation(webTranslation);
        translateRecord.setExplain(explain);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        translateRecord.setDate(date);
        translateRecord.save();
    }
}
