package cn.doug.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import cn.doug.system.model.entity.SysDept;
import cn.doug.system.plugin.mybatis.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    @DataPermission(deptIdColumnName = "id")
    @Override
    List<SysDept> selectList(@Param(Constants.WRAPPER) Wrapper<SysDept> queryWrapper);
}
