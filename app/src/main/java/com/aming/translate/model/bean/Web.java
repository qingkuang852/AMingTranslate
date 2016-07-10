package com.aming.translate.model.bean;

import java.util.ArrayList;

/**
 * Created by wenming on 2016/6/26.
 */
public class Web {
    public String key;
    public ArrayList<String> value;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append(" ---> ");
        for (int i = 0; i < value.size(); i++) {
            builder.append(value.get(i));
            if (i!=value.size()-1){
                builder.append(",");
            }
        }
        builder.append("\n");
        return builder.toString();
    }
}
