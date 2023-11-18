package com.example.asm.services.impl;

import com.example.asm.entity.ChiTietSp;
import com.example.asm.repositories.ChiTietSPRepository;
import com.example.asm.services.ChiTietSanPhamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietSPServiceImpl implements ChiTietSanPhamService {
    @Autowired
    private ChiTietSPRepository chiTietSPRepository;

    @Override
    public List<ChiTietSp> findAll() {
        return chiTietSPRepository.findAll();
    }

    @Override
    public ChiTietSp getById(UUID id) {
        if (chiTietSPRepository.findById(id).isPresent()) {
            return chiTietSPRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void add(ChiTietSp chiTietSp) {
        if (chiTietSp.getId() == null) {
            chiTietSPRepository.save(chiTietSp);
        }
    }

    @Override
    public void update(ChiTietSp chiTietSp) {
        ChiTietSp chiTietSpUpdate = chiTietSPRepository.findById(chiTietSp.getId()).get();
        chiTietSpUpdate.setNamBaoHanh(chiTietSp.getNamBaoHanh());
        chiTietSpUpdate.setMoTa(chiTietSp.getMoTa());
        chiTietSpUpdate.setSoLuongTon(chiTietSp.getSoLuongTon());
        chiTietSpUpdate.setGiaNhap(chiTietSp.getGiaNhap());
        chiTietSpUpdate.setGiaBan(chiTietSp.getGiaBan());
        chiTietSpUpdate.setSanPham(chiTietSp.getSanPham());
        chiTietSpUpdate.setNsx(chiTietSp.getNsx());
        chiTietSpUpdate.setMauSac(chiTietSp.getMauSac());
        chiTietSpUpdate.setDongSp(chiTietSp.getDongSp());
        chiTietSPRepository.save(chiTietSpUpdate);
    }


    @Override
    public void deleteById(UUID id) {
        ChiTietSp chiTietSp = chiTietSPRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + id));

        try {
            chiTietSPRepository.delete(chiTietSp);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Không thể xóa sản phẩm với ID: " + id
                    + " do có bản ghi liên quan trong bảng Khác.");
        }
    }

    @Override
    public List<ChiTietSp> findBySanPhamId(UUID id) {
        return chiTietSPRepository.findBySanPhamId(id);
    }

    @Override
    public List<ChiTietSp> findByTenDongSP(String tenDongSP) {
        return chiTietSPRepository.findByTenDongSP(tenDongSP);
    }

    @Override
    public String findTenDongSP(UUID id) {
        return chiTietSPRepository.findTenDongSP(id);
    }

    @Override
    public Integer getSoLuong(UUID idChiTietSp) {
        return chiTietSPRepository.getSoLuong(idChiTietSp);
    }

    @Override
    public void updateSoLuong(Integer soLuong, UUID idChiTietSp) {
        chiTietSPRepository.updateSoLuong(soLuong, idChiTietSp);
    }

    @Override
    public Page<ChiTietSp> findAllPage(int page) {
        Pageable pageable = PageRequest.of(page,5);
        return chiTietSPRepository.findAll(pageable);
    }

    @Override
    public ChiTietSp detail(UUID id) {
        return chiTietSPRepository.findById(id).get();
    }
}
