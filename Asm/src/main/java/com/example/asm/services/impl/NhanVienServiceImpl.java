package com.example.asm.services.impl;


import com.example.asm.entity.NhanVien;
import com.example.asm.repositories.ChucVuRepository;
import com.example.asm.repositories.CuaHangRepository;
import com.example.asm.repositories.NhanVienRepository;
import com.example.asm.services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private CuaHangRepository cuaHangRepository;


    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien getById(UUID id) {
        if (nhanVienRepository.findById(id).isPresent()) {
            return nhanVienRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void add(NhanVien nhanVien) {
        if (nhanVien.getId() == null) {
            nhanVienRepository.save(nhanVien);
        }
    }

    @Override
    public void update(NhanVien nhanVien) {
        NhanVien nhanVienUpdate = nhanVienRepository.findById(nhanVien.getId()).get();
        nhanVienUpdate.setTen(nhanVien.getTen());
        nhanVienUpdate.setTenDem(nhanVien.getTenDem());
        nhanVienUpdate.setHo(nhanVien.getHo());
        nhanVienUpdate.setGioiTinh(nhanVien.getGioiTinh());
        nhanVienUpdate.setTrangThai(nhanVien.getTrangThai());
        nhanVienUpdate.setNgaySinh(nhanVien.getNgaySinh());
        nhanVienUpdate.setDiaChi(nhanVien.getDiaChi());
        nhanVienUpdate.setSdt(nhanVien.getSdt());
        nhanVienUpdate.setMatKhau(nhanVien.getMatKhau());
        nhanVienUpdate.setEmail(nhanVien.getEmail());
        nhanVienUpdate.setChucVu(chucVuRepository.findById(nhanVien.getChucVu().getId()).get());
        nhanVienUpdate.setCuaHang(cuaHangRepository.findById(nhanVien.getCuaHang().getId()).get());
        nhanVienRepository.save(nhanVienUpdate);
    }

    @Override
    public NhanVien detail(UUID id) {
        return nhanVienRepository.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        if (nhanVienRepository.findById(id).isPresent()) {
            nhanVienRepository.deleteById(id);
        }
    }

//    @Override
//    public NhanVienViewModel checkLogin(LoginAdminRequest loginAdminRequest) {
//        NhanVien nhanVien = nhanVienRepository.login(loginAdminRequest.getEmail(), loginAdminRequest.getMatKhau());
//        if (nhanVien != null) {
//            return nhanVienConvert.toModel(nhanVien);
//        } else {
//            return null;
//        }
//    }


    @Override
    public String maNVCount() {
        String code = "";
        List<NhanVien> list = nhanVienRepository.findAll();
        if (list.isEmpty()) {
            code = "NV01";
        } else {
            int max = 0;
            for (NhanVien nv : list) {
                String ma = nv.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "NV0" + max;
            } else {
                code = "NV" + max;
            }
        }
        return code;
    }

    @Override
    public Page<NhanVien> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return nhanVienRepository.findAll(pageable);
    }

    @Override
    public boolean checkMa(String ma) {
        return nhanVienRepository.existsNhanVienByMa(ma);
    }
}

