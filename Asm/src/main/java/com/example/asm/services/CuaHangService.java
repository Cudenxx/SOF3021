package com.example.asm.services;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.CuaHang;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CuaHangService {

    public List<CuaHang> findAll();
    public CuaHang getById(UUID id);
    public void add(CuaHang cuaHang);
    public void deleteById(UUID id);

    public String maCHCount();

    public CuaHang detail(UUID id);

    public void update(CuaHang cuaHang);
    public Page<CuaHang> findAllPage(int page);


}
