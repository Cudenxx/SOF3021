package com.example.asm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "KhachHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class KhachHang implements Serializable {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Ma")
    private String ma;

    @NotBlank(message = "Không được để trống")
    @Column(name = "Ten")
    private String ten;

    @NotBlank(message = "Không được để trống")
    @Column(name = "TenDem")
    private String tenDem;

    @NotBlank(message = "Không được để trống")
    @Column(name = "Ho")
    private String ho;

    @NotNull(message = "Không được để trống")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "ThanhPho")
    private String thanhPho;

    @Column(name = "QuocGia")
    private String quocGia;

    @Column(name = "Sdt")
    private String sdt;

    @NotBlank(message = "Không được để trống")
    @Column(name = "MatKhau")
    private String matKhau;

    @NotBlank(message = "Không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "khachHang",fetch = FetchType.LAZY)
    private List<GioHang> listGioHang;

    @OneToMany(mappedBy = "khachHang",fetch = FetchType.LAZY)
    private List<HoaDon> listHoaDon;

}
