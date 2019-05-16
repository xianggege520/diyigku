package cn.itrip.common;

import cn.itrip.beans.pojo.ItripUser;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ValidationToken {
    private Logger logger = Logger.getLogger(ValidationToken.class);

    @Autowired(required = false)
    private RedisAPI redisAPI;

    public ItripUser getCurrentUser(String tokenString){
        System.out.println("1");
        ItripUser itripUser = null;
        if( EmptyUtils.isEmpty(tokenString)){    //if(null == tokenString || "".equals(tokenString)){

            System.out.println("2");
            return null;
        }
        System.out.println("3");
        try{
            System.out.println("4");
            String userInfoJson = redisAPI.get(tokenString);
            System.out.println("5");
            itripUser = JSONObject.parseObject(userInfoJson,ItripUser.class);
            System.out.println("6");
            System.out.println(itripUser.getUserCode());
        }catch(Exception e){
            itripUser = null;
            logger.error("get userinfo from redis but is error : " + e.getMessage());
        }

        return itripUser;
    }
}

