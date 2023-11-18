package com.example.asm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SanPham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SanPham implements Serializable {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(name = "Ma")
    private String ma;
    @Size(min = 1, max = 200, message = "Số lượng ký tự lớn hơn 1 và nhỏ hơn 200")
    @NotBlank(message = "Không được để trống")
    @Column(name = "Ten")
    private String ten;
    @NotNull(message = "Không được để trống")
    @Column(name = "Anh", columnDefinition = "Text")
    private String anh;
    @OneToMany(mappedBy = "sanPham",fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSP;

}


    

   

