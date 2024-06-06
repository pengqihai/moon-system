package cn.doug.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 地区表实体
 *
 * @author pengqihai
 * @since 2024-06-05
 */
@Getter
@Setter
@TableName("t_region")
public class Region implements Serializable {

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
     * 地区父id
     */
    private String regionParentId;

    /**
     * 地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县
     */
    private Integer regionLevel;

    /**
     * 逻辑删除标识(0:未删除;1:已删除)
     */
    private Integer isDeleted;
}
