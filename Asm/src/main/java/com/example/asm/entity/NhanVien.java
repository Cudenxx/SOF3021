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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NhanVien implements Serializable {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Mã không được để trống")
    @Column(name = "Ma")
    private String ma;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Tên không được để trống")
    @Column(name = "Ten")
    private String ten;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Tên đệm không được để trống")
    @Column(name = "TenDem")
    private String tenDem;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Họ không được để trống")
    @Column(name = "Ho")
    private String ho;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @NotNull(message = "Ngày sinh không được để trống")
    @Column(name = "NgaySinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngaySinh;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Địa chỉ không được để trống")
    @Column(name = "DiaChi")
    private String diaChi;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Column(name = "Sdt")
    private String sdt;

    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Mật khẩu không được để trống")
    @Column(name = "MatKhau")
    private String matKhau;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Column(name = "Email")
    private String email;

    @NotNull(message = "Bạn chưa chọn cửa hàng")
    @ManyToOne
    @JoinColumn(name = "IdCH")
    private CuaHang cuaHang;

    @NotNull(message = "Bạn chưa chọn chức vụ")
    @ManyToOne
    @JoinColumn(name = "IdCV")
    private ChucVu chucVu;

    @Column(name = "IdGuiBC")
    private UUID idGuiBC;

    @Column(name = "TrangThai")
    private int trangThai;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
    private List<HoaDon> listHoaDon;


}
