package cn.doug.system.controller.file;

import cn.doug.common.annotation.WebLog;
import cn.doug.common.result.vo.ResultVO;
import cn.doug.system.model.dto.FileInfo;
import cn.doug.system.service.OssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "07.文件接口")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final OssService ossService;

    @PostMapping
    @WebLog(value = "文件上传")
    @Operation(summary = "文件上传")
    public ResultVO<FileInfo> uploadFile(
            @Parameter(description ="表单文件对象") @RequestParam(value = "file") MultipartFile file
    ) {
        FileInfo fileInfo = ossService.uploadFile(file);
        return ResultVO.success(fileInfo);
    }

    @DeleteMapping
    @WebLog(value = "文件删除")
    @Operation(summary = "文件删除")
    @SneakyThrows
    public ResultVO deleteFile(
            @Parameter(description ="文件路径") @RequestParam String filePath
    ) {
        boolean result = ossService.deleteFile(filePath);
        return ResultVO.judge(result);
    }
}
