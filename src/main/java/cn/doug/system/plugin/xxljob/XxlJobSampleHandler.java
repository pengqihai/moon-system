package cn.doug.system.plugin.xxljob;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * xxl-job 测试示例（Bean模式）
 */
@Component
@Log4j2
public class XxlJobSampleHandler {

    @XxlJob("demoJobHandler")
    public void demoJobHandler() {
        log.info("XXL-JOB, Hello World.");
    }

}
