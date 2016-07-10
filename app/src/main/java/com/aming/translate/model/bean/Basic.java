package com.aming.translate.model.bean;

import java.util.ArrayList;

/**
 * Created by wenming on 2016/6/26.
 */
public class Basic {
    public String phonetic;
    public ArrayList<String> explains;

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < explains.size(); i++) {
            String explain = explains.get(i);
            builder.append(explain);
            if (i!=explains.size()-1){
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
