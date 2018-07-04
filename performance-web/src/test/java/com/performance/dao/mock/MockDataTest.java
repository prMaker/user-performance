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
    private SqlSessionTemplate sqlSessionTemplate;

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
/*
user_info_id,login_id,id_card,user_name,birthday,sex,phone,is_deleted,pid,dispostion,created_time,modified_time,created_user_info_id,modified_user_info_id


*/
        String statement = "INSERT INTO user_info " +
                "(login_id,id_card,user_name,birthday,sex,phone,is_deleted,pid,dispostion,created_time,modified_time,created_user_info_id,modified_user_info_id) " +
                " VALUES " +
                "(9999999, 410882199009013413,'测试', '20190105' , 1, 18832058799, 0, 99999, 4, '2018-06-27 22:26:15', '2018-06-27 22:26:15', 1, 1);";

        sqlSessionTemplate.insert(statement);
    }




}
