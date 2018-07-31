package cn.wan.sayhello;

import cn.wan.sayhello.pageprocessor.SayHelloPageProcessor;
import cn.wan.sayhello.pipeline.DbPipeline;
import cn.wan.sayhello.util.Constant;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Spider;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    private static ApplicationContext context;

    public static void main(String[] args) {
        init();
        DbPipeline pipeline = (DbPipeline) context.getBean("dbPipeline");
        Spider.create(new SayHelloPageProcessor())
                .addPipeline(pipeline)
                .addUrl(Constant.getConfig("startUrl"))
                .thread(Constant.getInt("thread"))
                .run();
    }

    private static void init() {
        PropertyConfigurator.configure("E:\\idea_workspace\\sayhello\\src\\main\\resources\\log4j.properties");
        context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml","classpath:spring/spring-dao.xml");
    }
}