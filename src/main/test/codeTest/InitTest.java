package codeTest;

import cn.edu.nwsuaf.listener.DataTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by luxiaoping on 2016/8/8.
 */
public class InitTest {

    public static void main(String args[]){
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring-context.xml");

        DataTask dataTask = context.getBean(DataTask.class);
        dataTask.run();


    }

}
