package com.example.asm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "GioHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GioHang implements Serializable {

        @Id
        @Column(name="Id")
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID Id;

        @ManyToOne
        @JoinColumn(name = "idKH")
        private KhachHang khachHang;

        @Column(name = "idNV")
        private String idNV;

        @Column(name = "Ma")
        private String ma;

        @Column(name = "NgayTao")
        private Date ngayTao;

        @Column(name = "NgayThanhToan")
        private Date ngayThanhToan;

        @Column(name = "TenNguoiNhan")
        private String tenNguoiNhan;

        @Column(name = "DiaChi")
        private String diaChi;

        @Column(name = "Sdt")
        private String sdt;

        @Column(name = "TinhTrang")
        private int trangThai;

        @OneToMany(mappedBy = "gioHang", fetch = FetchType.LAZY)
        private List<GioHangChiTiet> listGioHangChiTiet;


}
