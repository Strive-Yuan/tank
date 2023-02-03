package com.api.controller;

import com.api.response.ServerResponseEntity;
import com.api.utils.HttpUtil;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ExchangeRateController {

    private final static Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);

    @GetMapping("api/exchange_rate")
    public ServerResponseEntity<String> login(@Param(value = "date")String date) throws Exception {
        List<Header> headers = List.of(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("date", date);
        String result = HttpUtil.doGET("http://www.safe.gov.cn/AppStructured/hlw/jsonRmb.do", headers, paramMap);
        logger.info("result->{}",result);
        // 生成token
        return ServerResponseEntity.success(result);
    }
}
