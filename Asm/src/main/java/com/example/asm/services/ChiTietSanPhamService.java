package com.example.asm.services;

import com.example.asm.entity.ChiTietSp;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {
    public List<ChiTietSp> findAll();
    public ChiTietSp getById(UUID id);
    public void add(ChiTietSp chiTietSp);
    public void update(ChiTietSp chiTietSp);
    public void deleteById(UUID id);
    public List<ChiTietSp> findBySanPhamId(UUID id);
    public List<ChiTietSp> findByTenDongSP(String tenDongSP);
    public String findTenDongSP(UUID id);
    public Integer getSoLuong(UUID idChiTietSp);
    public void updateSoLuong(Integer soLuong, UUID idChiTietSp);
    public Page<ChiTietSp> findAllPage(int page);

    public ChiTietSp detail(UUID id);
}
