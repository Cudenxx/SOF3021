package com.example.asm.services;


import com.example.asm.entity.ChucVu;
import com.example.asm.entity.SanPham;
import com.example.asm.model.SanPhamModel;
import org.springframework.data.domain.Page;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public interface SanPhamService {

    public List<SanPham> findAll();
    public SanPham getById(UUID id);

    public SanPham detail(UUID id);
    public void add(SanPhamModel sanPhamModel, Path path);
    public void update(SanPhamModel sanPhamModel,Path path);
    public void deleteById(UUID id);
    public Page<SanPham> findAllPage(int page);
    public String maSPcount();


}
