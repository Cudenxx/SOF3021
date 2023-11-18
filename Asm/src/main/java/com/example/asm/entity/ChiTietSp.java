
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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "ChiTietSP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ChiTietSp {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @NotNull(message = "Năm bảo hành không được để trống")
    @Min(value = 1, message = "Năm bảo hành không được để trống")
    @Column(name = "NamBH")
    private Integer namBaoHanh;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Mô tả không được để trống")
    @Column(name = "MoTa")
    private String moTa;

    @Min(value = 1, message = "Số lượng tồn không được để trống")
    @Max(value = 1000, message = "Số ký tự không quá 1000")
    @NotNull(message = "Số lượng tồn không được để trống")
    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    @DecimalMin(value = "0.0", message = "Giá nhập không được nhỏ hơn 0")
    @DecimalMax(value = "1000000000.0", message = "Giá nhập không được lớn hơn 1000000000")
    @NotNull(message = "Giá nhập không được để trống")
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;

    @DecimalMin(value = "0.0", message = "Giá bán không được nhỏ hơn 0")
    @DecimalMax(value = "1000000000.0", message = "Giá nhập không được lớn hơn 1000000000")
    @NotNull(message = "Giá bán không được để trống")
    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @NotNull(message = "Bạn chưa chọn sản phẩm")
    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;

    @NotNull(message = "Bạn chưa chọn nhà sản xuất")
    @ManyToOne
    @JoinColumn(name = "IdNsx")
    private NSX nsx;

    @NotNull(message = "Bạn chưa chọn màu sắc")
    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @NotNull(message = "Bạn chưa chọn dòng sản phẩm")
    @ManyToOne
    @JoinColumn(name = "IdDongSP")
    private DongSp dongSp;

    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    private List<GioHangChiTiet> listGioHangChiTiet;

    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> listHoaDonChiTiet;
}
