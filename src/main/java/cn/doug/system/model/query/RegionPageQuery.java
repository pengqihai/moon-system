package cn.doug.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 地区表分页查询对象
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Schema(description ="地区表分页查询对象")
@Data
public class RegionPageQuery {

    @Schema(description="关键字")
    private String keywords;

}
