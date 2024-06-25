package cn.doug.system.model.query;

import cn.doug.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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

}
