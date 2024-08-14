package cn.doug.system.converter;

import cn.doug.system.model.bo.SysUserBO;
import cn.doug.system.model.form.SysUserForm;
import cn.doug.system.model.vo.SysUserImportVO;
import cn.doug.system.model.vo.SysUserInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.entity.SysUser;
import cn.doug.system.model.vo.SysUserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author pengqihai
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface SysUserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getGender(), cn.doug.common.enums.GenderEnum.class))")
    })
    SysUserPageVO bo2PageVo(SysUserBO bo);

    Page<SysUserPageVO> bo2PageVo(Page<SysUserBO> bo);

    SysUserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(SysUserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    SysUserInfoVO toUserInfoVo(SysUser entity);

    SysUser importVo2Entity(SysUserImportVO vo);

}
