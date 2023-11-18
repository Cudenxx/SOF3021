package com.example.asm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
//@IdClass(HoaDonChiTietId.class)
@Table(name = "HoaDonChiTiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "IdChiTietSp")
    private ChiTietSp chiTietSp;

    @Column(name = "SoLuong")
    private Integer soLuongTon;

    @Column(name = "DonGia", columnDefinition = "Decimal(20,0)")
    private BigDecimal donGia;


}
