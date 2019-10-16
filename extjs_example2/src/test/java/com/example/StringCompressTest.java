package com.example;

import com.example.util.ZipUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;

public class StringCompressTest {
    @Test
    public void test1() {
        ZipUtils zipUtils = compressInstance();
        String str = "\"abcccc   asdsad sadfsfwqer0r0q38s aslfjoqiurq\"";
        System.out.println(str.length());
        String gzip = zipUtils.gzip(str);
        System.out.println(gzip.length());
        String zip = zipUtils.zip(str);
        System.out.println(zip.length());
        /**
         * 47
         * 86
         * 128
         */
    }

    public ZipUtils compressInstance() {
        return new ZipUtils();
    }

    @Test
    public void test2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\搜狗高速下载\\企业版操作手册.docx")));
        StringBuffer sf = new StringBuffer();
        String data = null;
        while((data = br.readLine()) != null) {
            sf.append(data);
        }
        // 输入流的关闭
        String test = sf.toString();

//        String test = "{\"valueId\":\"00156618323548724721005056a664aa\",\"v\":0,\"lockstate\":0,\"duration\":0,\"totalDuration\":600,\"disabled\":0,\"sbxSuggested\":0,\"faqCount\":0,\"faqRemovedCount\":0,\"editor\":\"admin\",\"editTime\":\"2019-08-19 10:53\",\"createTime\":\"2019-08-19 10:53\",\"objectName\":\"标准化咨询服务产品购买\",\"objectId\":\"00156618323547824720005056a664aa\",\"objectStatus\":0,\"catePath\":\"/预置知识测试/标准化咨询服务产品\",\"cateIdPath\":\"/ff8080816ca63f2b016ca7ca031f0001/7a00c24b9d234d27b1b73b355e11803e\",\"op\":0,\"flag\":0,\"visible\":true,\"state\":1,\"values\":[{\"id\":\"00156618323559524912005056a664aa\",\"value\":\"原申万客户操作流程：登录网址http://www.sywg.com 后“产品中心”页面——在“咨询服务产品”栏目下点选要购买的产品，进入产品介绍页面——阅读产品购买协议书、产品说明书，点击最下方的“我已阅读并理解以上信息，确认”——确认接受服务的手机号码（必须正确无误），若手机号不对或想变更手机号码，点击“更换手机”按钮即可变更服务手机号码。\\n注意：1、此手机号的变更会同步到账户系统。电子邮箱信息客户可自愿填写，非必填项。2、在“产品购买使用期限”选择购买使用期限。（目前产品购买使用期限为：3个月、6个月和12个月）。\\n原宏源客户操作流程：1、网站购买流程：\\n（1）网址http://www.hysec.com 理财产品超市中找到自己想要购买的产品点击“我要购买”；\\n（2）用账号密码登录，根据提示签署相关协议；\\n（3）首次签约成功提示，下个交易日，即可查看签约产品；\\n（4）签约成功后，在“我的金宏源”栏目下点击“风格组合”即可查询产品。\\n2、网上交易软件购买流程：\\n登录增强版行情交易客户端，点击“自助”中的“投顾产品”菜单，通过“产品目录”子菜单选择想要购买的产品点击前面的购买按钮，根据提示操作。\",\"dimTagIds\":[],\"dimFlag\":0,\"dimop\":0}],\"question\":\"标准化咨询服务产品购买流程\",\"faqs\":[]}";
//        String test = "{\"valueId\":\"00156618323572725088005056a664aa\",\"v\":0,\"lockstate\":0,\"duration\":0,\"totalDuration\":600,\"disabled\":0,\"sbxSuggested\":0,\"faqCount\":0,\"faqRemovedCount\":0,\"editor\":\"admin\",\"editTime\":\"2019-08-19 10:53\",\"createTime\":\"2019-08-19 10:53\",\"objectName\":\"标准化咨询服务产品签约\",\"objectId\":\"00156618323571925085005056a664aa\",\"objectStatus\":0,\"catePath\":\"/预置知识测试/标准化咨询服务产品\",\"cateIdPath\":\"/ff8080816ca63f2b016ca7ca031f0001/7a00c24b9d234d27b1b73b355e11803e\",\"op\":0,\"flag\":0,\"visible\":true,\"state\":1,\"values\":[{\"id\":\"00156618323580525200005056a664aa\",\"value\":\"首次签约当天不能变更产品，产品变更成功提示，下月生效，生效后可查看签约产品\\n\\n原申万产品替换流程：\\n1、登录http://www.sywg.com 选择“产品中心”——“咨询服务产品”——右上角登录“我的产品”——里面进行产品的替换。\\n2、关注微信公众号“申万宏源证券”——“我的服务”——“我的账户”先绑定普通资金账号——“签约服务”——“免费签约”——“签约管理”——选择替换的产品点击“替换”按钮——阅读风险揭示书并确认——阅读并签署“签约服务协议”，点击“确定”——填写客户信息，点击“免费签约”——点击“确定”，确认替换产品的签约信息。\\n\\n原宏源产品替换流程：\\n1、需要变更产品时，点击“产品中心”——“产品签约”——选择替换的产品——选中产品，点击“替换”按钮，替换成所选产品。\\n2、关注微信公众号“申万宏源证券”——“我的服务”——“我的账户”先绑定普通资金账号——“签约服务”——“免费签约”——“签约管理”——选择替换的产品点击“替换”按钮——阅读风险揭示书并确认——阅读并签署“签约服务协议”，点击“确定”——填写客户信息，点击“免费签约”——点击“确定”，确认替换产品的签约信息。\",\"dimTagIds\":[],\"dimFlag\":0,\"dimop\":0}],\"question\":\"标准化咨询服务产品替换流程\",\"faqs\":[]}";

        int a = test.getBytes().length;
        System.out.println("压缩前: " + test.getBytes().length);
        int b = compressInstance().zip(test).getBytes().length;
        System.out.println("zip压缩后: " + compressInstance().zip(test).getBytes().length + " 压缩率: " + new BigDecimal((float)b/a).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        int c = compressInstance().gzip(test).getBytes().length;
        System.out.println("gzip压缩后: " + compressInstance().gzip(test).getBytes().length + " 压缩率: " + new BigDecimal((float)c/a).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        System.out.println("unzip: " + compressInstance().unzip(compressInstance().zip(test)).getBytes().length);
        System.out.println("gunzip: " + compressInstance().gunzip(compressInstance().gzip(test)).getBytes().length);
    }
}
