package com.example.asm.services.impl;


import com.example.asm.entity.CuaHang;
import com.example.asm.repositories.CuaHangRepository;
import com.example.asm.services.CuaHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CuaHangServiceImpl implements CuaHangService {

    @Autowired
    private CuaHangRepository cuaHangRepository;

    @Override
    public List<CuaHang> findAll() {
        return cuaHangRepository.findAll();
    }

    @Override
    public CuaHang getById(UUID id) {
        if (cuaHangRepository.findById(id).isPresent()) {
            return cuaHangRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void add(CuaHang cuaHang) {
        if (cuaHang.getId() == null) {
            cuaHangRepository.save(cuaHang);
        }
    }

    @Override
    public void update(CuaHang cuaHang) {
        CuaHang cuaHangUpdate = cuaHangRepository.findById(cuaHang.getId()).get();
        cuaHangUpdate.setTen(cuaHang.getTen());
        cuaHangUpdate.setDiaChi(cuaHang.getDiaChi());
        cuaHangUpdate.setThanhPho(cuaHang.getThanhPho());
        cuaHangUpdate.setQuocGia(cuaHang.getQuocGia());
        cuaHangRepository.save(cuaHangUpdate);
    }

    @Override
    public Page<CuaHang> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return cuaHangRepository.findAll(pageable);
    }


    @Override
    public void deleteById(UUID id) {
        if (cuaHangRepository.findById(id).isPresent()) {
            cuaHangRepository.deleteById(id);
        }
    }

    @Override
    public CuaHang detail(UUID id) {
        return cuaHangRepository.findById(id).get();
    }

    @Override
    public String maCHCount() {
        String code = "";
        List<CuaHang> list = cuaHangRepository.findAll();
        if (list.isEmpty()) {
            code = "CH01";
        } else {
            int max = 0;
            for (CuaHang cuaHang : list) {
                String ma = cuaHang.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "CH0" + max;
            } else {
                code = "CH" + max;
            }
        }
        return code;
    }
}
