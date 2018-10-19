package com.jieyi.controller;


import com.jieyi.mapper.FPInfoMapper;
import com.jieyi.model.fpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

import com.jieyi.util.HMACSHA1Util;

import java.util.*;

import com.jieyi.util.SecurityHelper;
import com.jieyi.util.URLParamParse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Configuration
public class index {
    private Logger logger = LoggerFactory.getLogger(index.class);
    @Value("${appKey}")
    private String appKey;

    @Autowired
    private FPInfoMapper fpInfoMapper;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam String id, ModelMap model, RedirectAttributes redirectAttributes) {
        logger.info("id=" + id);


        if (id == null) {
            model.addAttribute("data", "无效链接");
            return "success";
            //return "index";
        }
        try {
            String decryptStr = SecurityHelper.decrypt(appKey, id);
            logger.info("decryptStr=" + decryptStr);
            Map<String, String> map = URLParamParse.URLParamParse(decryptStr);
            if (map.get("cardno") == null || map.get("key") == null) {
                model.addAttribute("data", "无效的二维码");
                return "success";
            }
            String cardno = map.get("cardno");
            String ref_key = map.get("key");

            //String sign=map.get("sign");
            logger.info("cardno=" + cardno);
            logger.info("ref_key=" + ref_key);
            fpInfo fi=fpInfoMapper.selectTxnData(cardno, ref_key);
            logger.info("fi.getCancelflag()="+fi.getCancelflag());
            logger.info("fi.getFp_status()="+fi.getFp_status());
            if(fi!=null){
                if(fi.getCancelflag().equals("Y")){
                    logger.info("原交易已撤销，不允许开发票");
                    model.addAttribute("data", "原交易已撤销，不允许开发票");
                    return "success";
                }
                if(fi.getFp_status().equals("1")){
                    logger.info("原交易已经开过发票");
                    model.addAttribute("data", "原交易已经开过发票。");
                    return "success";
                }
            }
            else{
                logger.info("原交易不存在");
                model.addAttribute("data", "原交易不存在");
                return "success";
            }
            model.addAttribute("data", id);
            return "index";
        } catch (Exception e) {
            model.addAttribute("data", e.getMessage());
            return "success";
        }

    }

}
