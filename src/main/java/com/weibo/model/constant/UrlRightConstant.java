package com.weibo.model.constant;

import java.util.ArrayList;
import java.util.List;

public class UrlRightConstant {
    // 非必须登录
    public static List<String> loginNotNecessaryUrlList = new ArrayList<String>();
    static {
    
        
        loginNotNecessaryUrlList.add("/isExistUsername");
        loginNotNecessaryUrlList.add("/user_Login");
        loginNotNecessaryUrlList.add("/goregister");
        loginNotNecessaryUrlList.add("/getImageCheckCode");
        loginNotNecessaryUrlList.add("/register");
        loginNotNecessaryUrlList.add("/signin");
        loginNotNecessaryUrlList.add("/loginAdmin");
        loginNotNecessaryUrlList.add("/user/account/public");
        loginNotNecessaryUrlList.add("/companys/shop/samples");
        loginNotNecessaryUrlList.add("/companys/shop");
      
    }

}