package cn.doug.system;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.TimeZone;

/**
 * @author: pengqihai@gogpay.cn
 * @description
 * @date: 2024/3/30 14:32
 */
//@EnableFeignClients(basePackages = "cn.doug")
//@EnableDiscoveryClient
@ComponentScan(basePackages = "cn.doug")
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static void main(final String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        final String port = environment.getProperty("server.port");
        final String contextPath = environment.getProperty("server.servlet.context-path");
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>> 接口文档路径:http://localhost:{}{}/swagger-ui.html", port, contextPath);
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>> 接口文档增强:http://localhost:{}{}/doc.html", port, contextPath);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
