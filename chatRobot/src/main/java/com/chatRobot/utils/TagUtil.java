package com.chatRobot.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/4.美国区网站中从标题中获取标签的优化
 */
public class TagUtil {
    public static String getTag(String articleTitle) {
        StringBuffer articleTag = new StringBuffer();
        String[] tags = articleTitle.split(" ");
        String[] groups = {"the","for","off","Off","before","Set","set","order","The","index","Index",
                "Best","Value","value","more","More","After","Before","only","Only","new","New",
                "From","from","till", "until","after","between","with","under","and","over","below",
                "about","much","little","best","better","what","where","why","how","which","cout","all",
                "Count","All","oven","Oven","select","Select","AAA","BBB","One","one","two","Two","three",
                "Three","sets","Sets"
        };
        for (int i = 0; i < tags.length; i++) {
            String tag = tags[i].replaceAll("\\d+","").replaceAll("\\p{Punct}","");
            boolean contains = Arrays.asList(groups).contains(tag);
            if(StringUtils.isNotEmpty(tag)){
                if(tag.length()>2){
                    if(contains){

                    }else{
                        articleTag.append(tag + ",");
                    }
                }
            }
        }
       return String.valueOf(articleTag);
    }
}
