package com.scnu.vip.service;

import com.scnu.vip.entity.Vip;

public interface VipService {
    int vipLoginService(String username,String password);

    int vipRegisterService(Vip vip);

    Vip vipQueryInfoService(int id);

    boolean updateVipInfo(Vip vip);

    boolean findUserName(String username);
}
