package com.performance.dao.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MockDataTest {

    @Resource
    private SqlSessionTemplate sqlTemplate;

    public void testT(){
        System.out.println("11111111111111111111");
    }


    @Test
    public void testC(){

        String login_name = "department" + Math.round(Math.random()*1000);
        int dispostion = 4;
        String created_user = "admin";
        String modified_user = "admin";
        int created_user_id = 1;
        int modified_user_id = 1;

        String statement = "INSERT INTO user_login " +
                "(is_deleted,login_name,password,dispostion,created_time,modified_time " +
                ",created_user,modified_user,created_user_id,modified_user_id) " +
                " VALUES " +
                "(0,'" + login_name + "','" + login_name + "'," + dispostion + ",'2018-05-30 11:54:12','2018-05-30 11:54:12' " +
                ",'" + created_user + "','" + created_user + "',"
                + created_user_id + "," + modified_user_id + ");";

//        sqlTemplate.insert(statement);
    }




}
