package com.example.asm.services.impl;

import com.example.asm.entity.KhachHang;
import com.example.asm.repositories.KhachHangRepository;
import com.example.asm.services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getById(UUID id) {
        if (khachHangRepository.findById(id).isPresent()) {
            return khachHangRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void add(KhachHang khachHang) {
        if (khachHang.getId() == null) {
            khachHangRepository.save(khachHang);
        }
    }

    @Override
    public void update(KhachHang khachHang) {
        KhachHang khachHangUpdate = khachHangRepository.findById(khachHang.getId()).get();
        khachHangUpdate.setTen(khachHang.getTen());
        khachHangUpdate.setTenDem(khachHang.getTenDem());
        khachHangUpdate.setHo(khachHang.getHo());
        khachHangUpdate.setNgaySinh(khachHang.getNgaySinh());
        khachHangUpdate.setDiaChi(khachHang.getDiaChi());
        khachHangUpdate.setThanhPho(khachHang.getThanhPho());
        khachHangUpdate.setQuocGia(khachHang.getQuocGia());
        khachHangUpdate.setSdt(khachHang.getSdt());
        khachHangUpdate.setMatKhau(khachHang.getMatKhau());
        khachHangUpdate.setEmail(khachHang.getEmail());
        khachHangRepository.save(khachHangUpdate);
    }

    @Override
    public KhachHang detail(UUID id) {
        return khachHangRepository.findById(id).get();
    }



    @Override
    public void deleteById(UUID id) {
        if (khachHangRepository.findById(id).isPresent()) {
            khachHangRepository.deleteById(id);
        }
    }


    @Override
    public String maKHCount() {
        String code = "";
        List<KhachHang> list = khachHangRepository.findAll();
        if (list.isEmpty()) {
            code = "KH01";
        } else {
            int max = 0;
            for (KhachHang khachHang : list) {
                String ma = khachHang.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "KH0" + max;
            } else {
                code = "KH" + max;
            }
        }
        return code;
    }

    @Override
    public Page<KhachHang> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return khachHangRepository.findAll(pageable);
    }
}