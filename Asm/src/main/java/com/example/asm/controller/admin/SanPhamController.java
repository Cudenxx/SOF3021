package com.example.asm.controller.admin;

import com.example.asm.entity.ChucVu;
import com.example.asm.entity.SanPham;
import com.example.asm.model.SanPhamModel;
import com.example.asm.repositories.SanPhamRepository;
import com.example.asm.services.SanPhamService;
import com.example.asm.services.impl.SanPhamServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    @Autowired
    private SanPham sanPham;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    ServletContext context;

    @Autowired
    HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String index(Model model,@RequestParam(defaultValue = "0", name = "page") int page) {
        session.setAttribute("thongBao", "");
        Page<SanPham> list = sanPhamService.findAllPage(page);
        model.addAttribute("list", list);
        model.addAttribute("view", "/WEB-INF/views/admin/san-pham/index.jsp");
        return "admin/layout";
    }

    @GetMapping("/create")
    public String create(Model model, SanPham sanPham) {
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("view", "/WEB-INF/views/admin/san-pham/create.jsp");
        return "admin/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("sanPham") SanPhamModel sanPhamModel, @RequestParam("anh") MultipartFile file, BindingResult bindingResult, Model model
    ) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "/../images/";
        Path uploadPath = Paths.get(uploadDir);
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/san-pham/create.jsp");
            return "admin/layout";
        }
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if (sanPhamModel.getTen().trim().isEmpty()) {
            model.addAttribute("error", "Không được để trống");
            model.addAttribute("view", "/WEB-INF/views/admin/san-pham/create.jsp");
            return "admin/layout";
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        sanPhamModel.setMa(sanPhamService.maSPcount());
        sanPhamService.add(sanPhamModel, filePath);
        return "redirect:/admin/san-pham/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("sanPham", sanPhamService.detail(id));
        model.addAttribute("view", "/WEB-INF/views/admin/san-pham/update.jsp");
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("sanPham") SanPhamModel sanPhamModel, BindingResult bindingResult, Model model,
                         @RequestParam("anh") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "/../images/";
        Path uploadPath = Paths.get(uploadDir);
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", "/WEB-INF/views/admin/san-pham/update.jsp");
            return "admin/layout";
        }
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        sanPhamService.add(sanPhamModel, filePath);
        return "redirect:/admin/san-pham/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            sanPhamService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa sản phẩm do có bản ghi liên quan trong bảng ChiTietSP.");
        }
        return "redirect:/admin/san-pham/index";
    }
}
