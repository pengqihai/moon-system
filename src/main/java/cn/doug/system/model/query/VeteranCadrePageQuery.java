package cn.doug.system.model.query;

import cn.doug.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 老干部工作人员与离退休党员	分页查询对象
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Schema(description ="老干部工作人员与离退休党员	分页查询对象")
@Data
public class VeteranCadrePageQuery extends BasePageQuery {

    /**
     * 关键字
     */
    @Schema(description="关键字")
    private String keywords;

    @Schema(description="创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Schema(description="创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;

}
