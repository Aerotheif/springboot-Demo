package com.aerothief.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class FastJsonTest {
    @Test
    public void fastJson(){
        JSONObject jsonObjectSecond=new JSONObject();
        JSONObject jsonObjectFirst=new JSONObject();
        jsonObjectSecond.put("name","value");
        jsonObjectFirst.put("name",jsonObjectSecond);
    }
}
