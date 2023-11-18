package com.example.asm.services.impl;


import com.example.asm.entity.ChucVu;
import com.example.asm.repositories.ChucVuRepository;
import com.example.asm.services.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    private ChucVuRepository chucVuRepository;


    @Override
    public List<ChucVu> findAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public ChucVu getById(UUID id) {
        if (chucVuRepository.findById(id).isPresent()) {
            return chucVuRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void add(ChucVu chucVu) {
        if (chucVu.getId() == null) {
            chucVuRepository.save(chucVu);
        }
    }

    @Override
    public void update(ChucVu chucVu) {
        ChucVu chucVuUpdate = chucVuRepository.findById(chucVu.getId()).get();
        chucVuUpdate.setTen(chucVu.getTen());
        chucVuRepository.save(chucVuUpdate);
    }

    @Override
    public Page<ChucVu> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return chucVuRepository.findAll(pageable);
    }

    @Override
    public ChucVu detail(UUID id) {
        return chucVuRepository.findById(id).get();
    }


    @Override
    public void deleteById(UUID id) {
        if (chucVuRepository.findById(id).isPresent()) {
            chucVuRepository.deleteById(id);
        }
    }


    @Override
    public String maCVCount() {
        String code = "";
        List<ChucVu> list = chucVuRepository.findAll();
        if (list.isEmpty()) {
            code = "CV01";
        } else {
            int max = 0;
            for (ChucVu chucVu : list) {
                String ma = chucVu.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "CV0" + max;
            } else {
                code = "CV" + max;
            }
        }
        return code;

    }

}


