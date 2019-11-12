package com.kaiz.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtilsTest {

    @Test
    public void mapTest(){
//        User user = new User();
//        user.setId(111L);
//        user.setName("kaiz");
//        Map<String, String> configMap = new HashMap<>();
//        configMap.put("name", "nameA");
//
//        List<String> excludeFiled = new ArrayList<>();
//        excludeFiled.add("id");
//        UserA userA = BeanMapperUtil.map(user, UserA.class,configMap,excludeFiled);
//        System.out.println(userA.toString());

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("kaiz1");
        User user2 = new User();
        user2.setId(2L);
        user2.setName("kaiz2");
        users.add(user1);
        users.add(user2);

        // User表中的name字段映射成UserA中的nameA
        Map<String, String> configMap = new HashMap<>();
        configMap.put("name", "nameA");

        //排除映射字段id
        List<String> excludeFiled = new ArrayList<>();
        excludeFiled.add("id");
        List<UserA> userAS = BeanMapperUtil.mapList(users, User.class, UserA.class,configMap,excludeFiled);

        for (UserA userA : userAS){
            System.out.println("id:"+userA.getId());
            System.out.println("nameA:"+userA.getNameA());
            System.out.println("nameB:"+userA.getNameB());
            System.out.println("nameC:"+userA.getNameC());

        }

    }
}
