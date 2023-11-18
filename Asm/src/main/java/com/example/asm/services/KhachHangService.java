package com.example.asm.services;


import com.example.asm.entity.ChucVu;
import com.example.asm.entity.KhachHang;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {

    public List<KhachHang> findAll();
    public KhachHang getById(UUID id);
//    public KhachHangViewModel register(KhachHangViewModel khachHangViewModel);
    public void deleteById(UUID id);
    public KhachHang detail(UUID id);
    public void add(KhachHang khachHang);
    public void update(KhachHang khachHang);

//    public KhachHangViewModel checkLogin(LoginUserRequest loginUserRequest);
    public String maKHCount();
    public Page<KhachHang> findAllPage(int page);


}
