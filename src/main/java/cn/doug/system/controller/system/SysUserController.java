package cn.doug.system.controller.system;

import cn.doug.common.plugin.annotation.WebLog;
import cn.doug.system.model.form.SysUserForm;
import cn.doug.system.model.vo.SysUserPageVO;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.doug.common.result.PageResult;
import cn.doug.common.result.Result;
import cn.doug.system.common.util.ExcelUtils;
import cn.doug.system.model.entity.SysUser;
import cn.doug.system.model.query.SysUserPageQuery;
import cn.doug.system.model.vo.SysUserExportVO;
import cn.doug.system.model.vo.SysUserImportVO;
import cn.doug.system.model.vo.SysUserInfoVO;
import cn.doug.common.plugin.annotation.PreventDuplicateSubmit;
import cn.doug.system.plugin.easyexcel.UserImportListener;
import cn.doug.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用户控制器
 *
 * @author pengqihai
 * @since 2022/10/16
 */
@Tag(name = "02.用户接口")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    @WebLog(value = "用户分页列表")
    @Operation(summary = "用户分页列表")
    @GetMapping("/page")
    public PageResult<SysUserPageVO> listPagedUsers(
            @ParameterObject SysUserPageQuery queryParams
    ) {
        IPage<SysUserPageVO> result = userService.listPagedUsers(queryParams);
        return PageResult.success(result);
    }

    @WebLog(value = "新增用户")
    @Operation(summary = "新增用户")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:user:add')")
    @PreventDuplicateSubmit
    public Result saveUser(
            @RequestBody @Valid SysUserForm userForm
    ) {
        boolean result = userService.saveUser(userForm);
        return Result.judge(result);
    }

    @WebLog(value = "用户表单数据")
    @Operation(summary = "用户表单数据")
    @GetMapping("/{userId}/form")
    public Result<SysUserForm> getUserForm(
            @Parameter(description = "用户ID") @PathVariable Long userId
    ) {
        SysUserForm formData = userService.getUserFormData(userId);
        return Result.success(formData);
    }

    @WebLog(value = "修改用户")
    @Operation(summary = "修改用户")
    @PutMapping(value = "/{userId}")
    @PreAuthorize("@ss.hasPerm('sys:user:edit')")
    public Result updateUser(
            @Parameter(description = "用户ID") @PathVariable Long userId,
            @RequestBody @Validated SysUserForm userForm) {
        boolean result = userService.updateUser(userId, userForm);
        return Result.judge(result);
    }

    @WebLog(value = "删除用户")
    @Operation(summary = "删除用户")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:user:delete')")
    public Result deleteUsers(
            @Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = userService.deleteUsers(ids);
        return Result.judge(result);
    }

    @WebLog(value = "修改用户密码")
    @Operation(summary = "修改用户密码")
    @PatchMapping(value = "/{userId}/password")
    @PreAuthorize("@ss.hasPerm('sys:user:password:reset')")
    public Result updatePassword(
            @Parameter(description = "用户ID") @PathVariable Long userId,
            @RequestParam String password
    ) {
        boolean result = userService.updatePassword(userId, password);
        return Result.judge(result);
    }

    @WebLog(value = "修改用户状态")
    @Operation(summary = "修改用户状态")
    @PatchMapping(value = "/{userId}/status")
    public Result updateUserStatus(
            @Parameter(description = "用户ID") @PathVariable Long userId,
            @Parameter(description = "用户状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = userService.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getStatus, status)
        );
        return Result.judge(result);
    }

    @WebLog(value = "获取当前登录用户信息")
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public Result<SysUserInfoVO> getCurrentUserInfo() {
        SysUserInfoVO userInfoVO = userService.getCurrentUserInfo();
        return Result.success(userInfoVO);
    }

    @WebLog(value = "用户导入模板下载")
    @Operation(summary = "用户导入模板下载")
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = "用户导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        String fileClassPath = "excel-templates" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();

        excelWriter.finish();
    }

    @WebLog(value = "导入用户")
    @Operation(summary = "导入用户")
    @PostMapping("/import")
    public Result importUsers(@Parameter(description = "部门ID") Long deptId, MultipartFile file) throws IOException {
        UserImportListener listener = new UserImportListener(deptId);
        String msg = ExcelUtils.importExcel(file.getInputStream(), SysUserImportVO.class, listener);
        return Result.success(msg);
    }

    @WebLog(value = "导出用户")
    @Operation(summary = "导出用户")
    @GetMapping("/export")
    public void exportUsers(SysUserPageQuery queryParams, HttpServletResponse response) throws IOException {
        String fileName = "用户列表.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        List<SysUserExportVO> exportUserList = userService.listExportUsers(queryParams);
        EasyExcel.write(response.getOutputStream(), SysUserExportVO.class).sheet("用户列表")
                .doWrite(exportUserList);
    }
}
