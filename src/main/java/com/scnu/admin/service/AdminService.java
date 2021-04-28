package com.scnu.admin.service;

import com.scnu.admin.entity.Admin;
import com.scnu.vip.entity.Vip;

import java.util.List;

public interface AdminService {
    boolean adminLoginService(Admin admin);

    List adminQueryService();

    boolean adminUpdateVipInfoService(Vip vip);

    Vip adminFindVipInfoService(String id);

    boolean adminDelVipService(Integer id);
}
