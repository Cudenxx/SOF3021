package com.example.asm.services.impl;


import com.example.asm.entity.DongSp;
import com.example.asm.repositories.DongSPRepository;
import com.example.asm.services.DongSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DongSPServiceImpl implements DongSPService {
    @Autowired
    private DongSPRepository dongSpRepository;


    @Override
    public List<DongSp> getAll() {
        return dongSpRepository.findAll();
    }

    @Override
    public DongSp getById(UUID id) {
        if (dongSpRepository.findById(id).isPresent()) {
            return dongSpRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void add(DongSp dongSp) {
        if (dongSp.getId() == null) {
            dongSpRepository.save(dongSp);
        }
    }

    @Override
    public void update(DongSp dongSp) {
        DongSp dongSpUpdate = dongSpRepository.findById(dongSp.getId()).get();
        dongSpUpdate.setTen(dongSp.getTen());
        dongSpRepository.save(dongSpUpdate);
    }

    @Override
    public DongSp detail(UUID id) {
        return dongSpRepository.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        if (dongSpRepository.findById(id).isPresent()) {
            dongSpRepository.deleteById(id);
        }
    }

    @Override
    public Page<DongSp> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return dongSpRepository.findAll(pageable);
    }

    @Override
    public String maDSPCount() {
        String code = "";
        List<DongSp> list = dongSpRepository.findAll();
        if (list.isEmpty()) {
            code = "DSP01";
        } else {
            int max = 0;
            for (DongSp dongSp : list) {
                String ma = dongSp.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "DSP0" + max;
            } else {
                code = "DSP" + max;
            }
        }
        return code;
    }

}
