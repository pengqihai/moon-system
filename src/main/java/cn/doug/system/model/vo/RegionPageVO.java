package cn.doug.system.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 地区表 分页VO
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Getter
@Setter
@Schema( description = "地区表分页视图对象")
public class RegionPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

        @Schema(description = "地区主键编号")

    private String regionId;

        @Schema(description = "地区名称")

    private String regionName;

        @Schema(description = "地区缩写")

    private String regionShortName;

        @Schema(description = "行政地区编号")

    private String regionCode;

        @Schema(description = "地区父区划")

    private String regionParentCode;

        @Schema(description = "地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县")

    private Integer regionLevel;
}
