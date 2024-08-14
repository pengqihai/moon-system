package cn.doug.system.controller;

import cn.doug.common.plugin.annotation.WebLog;
import cn.doug.system.common.util.ExcelUtils;
import cn.doug.system.model.vo.VeteranCadreExportVO;
import cn.doug.system.model.vo.VeteranCadrePageVO;
import cn.doug.system.model.vo.SysUserImportVO;
import cn.doug.system.plugin.easyexcel.VeteranCadreListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.doug.common.base.BaseIdForm;
import cn.doug.system.model.form.VeteranCadreForm;
import cn.doug.system.model.query.VeteranCadrePageQuery;
import cn.doug.system.service.VeteranCadreService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.doug.common.result.PageResult;
import cn.doug.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * 老干部工作人员与离退休党员	 前端控制器
 *
 * @author pengqihai
 * @since 2024-06-25
 */
@Tag(name = "老干部工作人员与离退休党员	接口")
@RestController
@RequestMapping("/api/cadre")
@RequiredArgsConstructor
public class VeteranCadreController {

    private final VeteranCadreService veteranCadreService;

    /**
     *老干部工作人员与离退休党员	分页列表
     *
     * @return
     */
    @WebLog(value = "老干部工作人员与离退休党员	分页列表")
    @Operation(summary = "老干部工作人员与离退休党员	分页列表")
    @GetMapping("/page")
    public PageResult<VeteranCadrePageVO> listPagedVeteranCadres(VeteranCadrePageQuery queryParams) {
        IPage<VeteranCadrePageVO> result = veteranCadreService.listPagedVeteranCadres(queryParams);
        return PageResult.success(result);
    }

    /**
     * 获取老干部工作人员与离退休党员	表单数据
     *
     * @param form 老干部工作人员与离退休党员	ID
     * @return
     */
    @WebLog(value = "老干部工作人员与离退休党员	表单数据")
    @Operation(summary = "老干部工作人员与离退休党员	表单数据")
    @PostMapping("getInfo")
    public Result<VeteranCadreForm> getInfo(@RequestBody @Validated BaseIdForm form) {
        VeteranCadreForm formData = veteranCadreService.getVeteranCadreFormData(form.getId());
        return Result.success(formData);
    }

    /**
     * 新增老干部工作人员与离退休党员	
     *
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    @WebLog(value = "新增老干部工作人员与离退休党员")
    @Operation(summary = "新增老干部工作人员与离退休党员")
    @PostMapping("/add")
    public Result saveVeteranCadre(@RequestBody @Validated VeteranCadreForm formData ) {
        boolean result = veteranCadreService.saveVeteranCadre(formData);
        return Result.judge(result);
    }

    /**
     * 修改老干部工作人员与离退休党员	
     *
     * @param formData 老干部工作人员与离退休党员	表单对象
     * @return
     */
    @WebLog(value = "修改老干部工作人员与离退休党员")
    @Operation(summary = "修改老干部工作人员与离退休党员")
    @PutMapping(value = "/edit")
    public Result updateVeteranCadre(@RequestBody @Validated VeteranCadreForm formData) {
        boolean result = veteranCadreService.updateVeteranCadre(formData);
        return Result.judge(result);
    }

    /**
     * 删除老干部工作人员与离退休党员	
     *
     * @param form 老干部工作人员与离退休党员	ID，多个以英文逗号(,)分割
     * @return
     */
    @WebLog(value = "删除老干部工作人员与离退休党员")
    @Operation(summary = "删除老干部工作人员与离退休党员")
    @DeleteMapping("/del")
    public Result deleteVeteranCadres(@RequestBody @Valid BaseIdForm form) {
        boolean result = veteranCadreService.deleteVeteranCadres(form);
        return Result.judge(result);
    }


    /**
     * 老干部工作人员与离退休党员导入模板下载
     * @param response
     * @throws IOException
     */
    @WebLog(value = "老干部工作人员与离退休党员导入模板下载")
    @Operation(summary = "用户导入模板下载")
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = "老干部工作人员与离退休党员导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        String fileClassPath = "excel-templates" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();

        excelWriter.finish();
    }

    /**
     * 导入老干部工作人员与离退休党员
     * @throws IOException
     */
    @WebLog(value = "导入老干部工作人员与离退休党员")
    @Operation(summary = "导入老干部工作人员与离退休党员")
    @PostMapping("/import")
    public Result importUsers(MultipartFile file) throws IOException {
        VeteranCadreListener veteranCadreListener = new VeteranCadreListener();
        String msg = ExcelUtils.importExcel(file.getInputStream(), SysUserImportVO.class, veteranCadreListener);
        return Result.success(msg);
    }

    /**
     * 导出老干部工作人员与离退休党员
     * @param response
     * @throws IOException
     */
    @WebLog(value = "导出老干部工作人员与离退休党员")
    @Operation(summary = "导出老干部工作人员与离退休党员")
    @GetMapping("/export")
    public void exportUsers(VeteranCadrePageQuery queryParams, HttpServletResponse response) throws IOException {
        String fileName = "老干部工作人员与离退休党员列表.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        List<VeteranCadreExportVO> exportUserList = veteranCadreService.listExportVeteranCadres(queryParams);
        EasyExcel.write(response.getOutputStream(), VeteranCadreExportVO.class).sheet("老干部工作人员与离退休党员列表")
                .doWrite(exportUserList);
    }
}
