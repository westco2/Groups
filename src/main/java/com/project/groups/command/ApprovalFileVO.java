package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalFileVO {
    private String filename; //파일이름
    private String filepath; //파일경로
    private String upload_dir; // 저장 디렉토리
    private String login_id; //회원가입 아이디
}
