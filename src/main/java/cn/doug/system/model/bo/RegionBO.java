package cn.doug.system.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 地区表
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Getter
@Setter
public class RegionBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地区主键编号
     */
    @TableId("region_id")
    private String regionId;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 地区缩写
     */
    private String regionShortName;

    /**
     * 行政地区编号
     */
    private String regionCode;

    /**
     * 地区父区划
     */
    private String regionParentCode;

    /**
     * 地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县
     */
    private Integer regionLevel;
}
