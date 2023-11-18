/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author anhkon
 */
@Entity(name = "NSX")
@Table(name = "NSX")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NSX implements Serializable{
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
    @OneToMany(mappedBy = "nsx", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSP;


    
}
