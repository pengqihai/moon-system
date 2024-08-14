package cn.doug.system.model.query;

import cn.doug.common.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通知公告表分页查询对象
 *
 * @author pengqihai
 * @since 2024-08-14
 */
@Schema(description ="通知公告表分页查询对象")
@Data
public class SysNoticePageQuery extends BasePageQuery {

    /**
     * 关键字
     */
    @Schema(description="关键字")
    private String keywords;

}
