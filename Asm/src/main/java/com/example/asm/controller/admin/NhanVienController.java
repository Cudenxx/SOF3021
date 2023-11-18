package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.CuaHang;
import com.example.asm.entity.NhanVien;
import com.example.asm.services.ChucVuService;
import com.example.asm.services.CuaHangService;
import com.example.asm.services.NhanVienService;
import com.example.asm.services.impl.ChucVuServiceImpl;
import com.example.asm.services.impl.CuaHangServiceImpl;
import com.example.asm.services.impl.NhanVienServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService = new NhanVienServiceImpl();
    @Autowired
    private ChucVuService chucVuService = new ChucVuServiceImpl();
    @Autowired
    public CuaHangService cuaHangService = new CuaHangServiceImpl();
    @Autowired
    public NhanVien nhanVien;
    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<NhanVien> list = nhanVienService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/nhan-vien/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<ChucVu> chucVuList = chucVuService.findAll();
        List<CuaHang> cuaHangList = cuaHangService.findAll();
        model.addAttribute("cuaHangList", cuaHangList);
        model.addAttribute("chucVuList", chucVuList);
        model.addAttribute("nhanVien", nhanVien);
        model.addAttribute("view", "/WEB-INF/views/admin/nhan-vien/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            List<ChucVu> chucVuList = chucVuService.findAll();
            List<CuaHang> cuaHangList = cuaHangService.findAll();
            model.addAttribute("cuaHangList", cuaHangList);
            model.addAttribute("chucVuList", chucVuList);
            model.addAttribute("view", "/WEB-INF/views/admin/nhan-vien/create.jsp");
            return "admin/layout";
        } else if (nhanVienService.checkMa(nhanVien.getMa())) {
            List<ChucVu> chucVuList = chucVuService.findAll();
            List<CuaHang> cuaHangList = cuaHangService.findAll();
            model.addAttribute("cuaHangList", cuaHangList);
            model.addAttribute("chucVuList", chucVuList);
            model.addAttribute("error","Mã đã tồn tại");
            model.addAttribute("view", "/WEB-INF/views/admin/nhan-vien/create.jsp");
            return "admin/layout";
        }
        nhanVienService.add(nhanVien);
        return "redirect:/admin/nhan-vien/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model) {
        List<ChucVu> chucVuList = chucVuService.findAll();
        List<CuaHang> cuaHangList = cuaHangService.findAll();
        model.addAttribute("cuaHangList", cuaHangList);
        model.addAttribute("chucVuList", chucVuList);
        model.addAttribute("nhanVien", nhanVienService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/nhan-vien/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ChucVu> chucVuList = chucVuService.findAll();
            List<CuaHang> cuaHangList = cuaHangService.findAll();
            model.addAttribute("cuaHangList", cuaHangList);
            model.addAttribute("chucVuList", chucVuList);
            return "admin/nhan-vien/create";
        }
        nhanVienService.update(nhanVien);
        return "redirect:/admin/nhan-vien/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        nhanVienService.deleteById(id);
        return "redirect:/admin/nhan-vien/index";
    }
}
