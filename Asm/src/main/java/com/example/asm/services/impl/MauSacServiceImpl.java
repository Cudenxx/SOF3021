package com.example.asm.services.impl;


import com.example.asm.entity.MauSac;
import com.example.asm.repositories.MauSacRepository;
import com.example.asm.services.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getById(UUID id) {
        if (mauSacRepository.findById(id).isPresent()) {
            return mauSacRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void add(MauSac mauSac) {
        if (mauSac.getId() == null) {
            mauSacRepository.save(mauSac);
        }
    }

    @Override
    public void update(MauSac mauSac) {
        MauSac mauSacUpdate = mauSacRepository.findById(mauSac.getId()).get();
        mauSacUpdate.setTen(mauSac.getTen());
        mauSacRepository.save(mauSacUpdate);
    }

    @Override
    public MauSac detail(UUID id) {
        return mauSacRepository.findById(id).get();
    }

    @Override
    public void deleteById(UUID id) {
        if (mauSacRepository.findById(id).isPresent()) {
            mauSacRepository.deleteById(id);
        }
    }

    @Override
    public Page<MauSac> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public String maMSCount() {
        String code = "";
        List<MauSac> list = mauSacRepository.findAll();
        if (list.isEmpty()) {
            code = "MS01";
        } else {
            int max = 0;
            for (MauSac mauSac : list) {
                String ma = mauSac.getMa();
                if (ma.length() >= 4) {
                    int so = Integer.parseInt(ma.substring(3));
                    if (so > max) {
                        max = so;
                    }
                }
            }
            max++;
            if (max < 1000) {
                code = "MS0" + max;
            } else {
                code = "MS" + max;
            }
        }
        return code;
    }
}

