package com.maisel.goeasy;

import com.google.gson.Gson;
import com.maisel.service.UserService;
import com.maisel.utils.PlaceUtil;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-09 17:40
 */
@Component
public class TestEasy{

    public void publish(Map map){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io",
                "BC-1f689cc0a7c44600826000d770d5c605");
        goEasy.publish("cmfzChannel",json);
    }
}
