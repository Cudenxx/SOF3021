package com.example.asm.services;




import com.example.asm.entity.ChucVu;
import com.example.asm.entity.NhanVien;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {

    public List<NhanVien> findAll();
    public NhanVien getById(UUID id);
    public NhanVien detail(UUID id);
    public void add(NhanVien nhanVien);
    public void update(NhanVien nhanVien);
    public void deleteById(UUID id);
//    public NhanVienViewModel checkLogin(LoginAdminRequest loginAdminRequest);
    public String maNVCount();
    public Page<NhanVien> findAllPage(int page);

    public boolean checkMa(String ma);
}
