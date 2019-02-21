package com.myblog.service;



import java.util.List;

import com.myblog.pojo.AdminLoginLog;

public interface AdminLoginLogService {

    List<AdminLoginLog> selectRencent(int adminId);

    int insert(AdminLoginLog adminLoginLog);

    int selectCountByAdminId(int adminId);
}
