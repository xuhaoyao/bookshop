package com.scnu.vip.service.impl;

import com.scnu.vip.dao.VipDao;
import com.scnu.vip.entity.Vip;
import com.scnu.vip.service.VipService;

public class VipServiceImpl implements VipService {
    private VipDao dao = new VipDao();

    @Override
    public int vipLoginService(String username, String password) {
        return dao.loginVerify(username,password);
    }

    @Override
    public int vipRegisterService(Vip vip) {
        return dao.addVip(vip);
    }

    @Override
    public Vip vipQueryInfoService(int id) {
        return dao.QueryVipInfo(id);
    }

    @Override
    public boolean updateVipInfo(Vip vip) {
        return dao.updateVipInfo(vip);
    }

    @Override
    public boolean findUserName(String username) {
        return dao.findUserName(username);
    }
}
