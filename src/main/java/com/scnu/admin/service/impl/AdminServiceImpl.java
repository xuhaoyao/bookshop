package com.scnu.admin.service.impl;

import com.scnu.admin.dao.AdminDao;
import com.scnu.admin.entity.Admin;
import com.scnu.admin.service.AdminService;
import com.scnu.vip.entity.Vip;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao dao = new AdminDao();

    @Override
    public boolean adminLoginService(Admin admin) {
        boolean flag = false;
        flag = dao.loginVerify(admin);
        return flag;
    }

    @Override
    public List adminQueryService() {
        List list = dao.queryVips();
        return list;
    }

    @Override
    public boolean adminUpdateVipInfoService(Vip vip) {
        boolean flag = dao.updateVip(vip);
        return flag;
    }

    @Override
    public Vip adminFindVipInfoService(String id) {
        Vip vip = dao.queryVip(id);
        return vip;
    }

    @Override
    public boolean adminDelVipService(Integer id) {
        return dao.delVip(id);
    }
}
