package com.performance.dao.forTest;

import org.junit.Test;

import javax.print.DocFlavor;

public class mock {

    private String oneInfo;

    @Test
    public void test(){
        System.out.println("----------------");
    }

    @Test
    public void getTestSql() {
        getTestLogin();
        getTestInfo();
        getTestPerformance();
    }

    public void getTestLogin() {
        for(int i = 62; i < 67; i ++){
            System.out.println("##开始---" + i + "-----" + i +" ------------------------");

//          部门经理  目前有 2、3、4  三个编号

//            getOneLogin(
//                    "department"
//                    , 4
//                    , "admin"
//                    , 1
//                    , i
//            , 1
//            );

//          中心经理 2、3、4下分别有5个
//            getOneLogin("center"
//            ,3  // 职级
//                    ,"department3" // info用户名
//                    ,4 // 管理用户登录ID
//                    ,i // 索引 自增
//                    ,4); // 管理用户信息ID


//          负责人 5、11、16下分别有5个
//            getOneLogin("leader"
//                    ,2  // 职级
//                    ,"center16" // info用户名
//                    ,16 // 管理用户登录ID
//                    ,i // 索引 自增
//                    ,16); // 管理用户信息ID

            //          leader下共有 普通员工 22,27,32   下分别有5个
            //          添加普通用户  23,28,33
            getOneLogin("common"
                    ,1  // 职级
                    ,"leader33" // info用户名
                    ,33 // 管理用户登录ID
                    ,i // 索引 自增
                    ,33); // 管理用户信息ID


            System.out.println("##结束---" + i + "-----" + i +" ------------------------");

        }
//        System.out.println("getTestLogin:-----------------");
//        System.out.println("");
//        System.out.println("------------------------------");
    }

    private void getTestInfo() {
        String statement = getOneInfo();
    }

    private void getTestPerformance() {

    }

    public String getOneLogin(String login_name_prefixx
            , int dispos, String createdUser
            , int created_userId, int index
            , int pid) {
        String login_name = login_name_prefixx + Math.round(Math.random()*1000);
        int dispostion = dispos;
        String created_user = createdUser;
        String modified_user = createdUser;
        int created_user_id = created_userId;
        int modified_user_id = created_userId;

        System.out.println("####################################statementLogin");
        String statementLogin = "INSERT INTO user_login " +
                "(user_info_id,is_deleted,login_name,password,dispostion,created_time,modified_time " +
                ",created_user,modified_user,created_user_id,modified_user_id) " +
                " VALUES " +
                "("+ index + ",0,'" + login_name + "','" + login_name + "'," + dispostion + ",'2018-05-30 11:54:12','2018-05-30 11:54:12' " +
                ",'" + created_user + "','" + created_user + "',"
                + created_user_id + "," + modified_user_id + ");";
        System.out.println(statementLogin);

        System.out.println("####################################statementInfo");
        String statementInfo = "INSERT INTO user_info\n" +
                "(login_id,id_card,user_name,birthday,sex,phone,is_deleted" +
                ",pid,dispostion,created_time,modified_time," +
                "created_user_info_id,modified_user_info_id)\n" +
                "VALUES\n" +
                "(" + index + ",'410882199009013439','" + login_name +"','20190105',1,18832058799,0" +
                "," + pid + ","+ dispos + ",now(),now()," +
                "" + created_user_id + "," + created_user_id + ");";
        System.out.println(statementInfo);

        System.out.println("####################################statementPerformance");

        int operatePosition = ++dispos;

        String statementPerformance = "INSERT INTO user_performance \n" +
                "(user_info_id,is_deleted,performance_content,performance_score\n" +
                ",operate_user_info_id,created_time,modified_time,is_locked\n" +
                ",operate_disposition,performance_time)\n" +
                "VALUES\n" +
                "(" + index + ",0,'哈哈哈，真厉害！',100\n" +
                "," + pid + ",now(),now(),0\n" +
                "," + operatePosition + ",'201010');";
        System.out.println(statementPerformance);

        return statementLogin;
    }

    public String getOneInfo() {

        String statement = "INSERT INTO user_info\n" +
                "(login_id,id_card,user_name,birthday,sex,phone,is_deleted,pid,dispostion,created_time,modified_time,created_user_id,modified_user_id)\n" +
                "VALUES\n" +
                "(1,'410882199009013439','admin','20190105',1,18832058799,0,0,99,now(),now(),0,0);";
        return statement;
    }
}
