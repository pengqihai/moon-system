package cn.doug.system.converter;

import cn.doug.system.model.bo.SysUserBO;
import cn.doug.system.model.form.sys.SysUserForm;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.doug.system.model.entity.SysUser;
import cn.doug.system.model.vo.sys.UserImportVO;
import cn.doug.system.model.vo.sys.UserInfoVO;
import cn.doug.system.model.vo.sys.UserPageVO;
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
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(cn.doug.common.base.IBaseEnum.getLabelByValue(bo.getGender(), cn.doug.common.enums.GenderEnum.class))")
    })
    UserPageVO bo2PageVo(SysUserBO bo);

    Page<UserPageVO> bo2PageVo(Page<SysUserBO> bo);

    SysUserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(SysUserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

    SysUser importVo2Entity(UserImportVO vo);

}
