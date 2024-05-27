package ru.utin.project_egar.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.utin.project_egar.models.db_models.PropertyDocument;
import ru.utin.project_egar.models.documents_models.Document;
import ru.utin.project_egar.utils.CreatePagesUtil;
import ru.utin.project_egar.models.sort_models.SortModel;
import ru.utin.project_egar.services.PropertyDocumentService;
import ru.utin.project_egar.utils.URLCreateUtil;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Controller
@RequestMapping("/documents")
public class DocumentController {
    private final PropertyDocumentService propertyDocumentService;
    private SortModel sortModel;

    @Autowired
    public DocumentController(PropertyDocumentService propertyDocumentService) {
        this.propertyDocumentService = propertyDocumentService;
        sortModel = new SortModel();
    }

    @GetMapping
    public String start(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "find", required = false) String finded) {
        if (page == null) {
            page = 0;
        }
        if (finded != null) {
            return URLCreateUtil.createURL(page, sortModel, finded);
        }
        return URLCreateUtil.createURL(page, sortModel);
    }

    @GetMapping(value = "/index")
    public String index(Model model,
                        @RequestParam(value = "page") Integer page,
                        @RequestParam(value = "find", required = false) String finded,
                        @RequestParam(value = "list") Integer list) {
        if (finded == null || finded.isEmpty()) {
            sortModel.setFind(null);

        } else {
            sortModel.setFind(finded);
        }
        model.addAttribute("pages", propertyDocumentService.getPages(finded));
        model.addAttribute("documents", propertyDocumentService.index(sortModel, page, list));
        model.addAttribute("list", CreatePagesUtil.LIST_IN_PAGE);
        model.addAttribute("types", Document.DOCUMENTS_ARRAY);
        return "documents/index";
    }

    @GetMapping("/sort/search")
    public String search(@RequestParam(value = "find", required = false) String finded) {
        if (finded.isEmpty()) {
            return "redirect:/documents/sort/search/reset";
        }
        finded = URLEncoder.encode(finded, StandardCharsets.UTF_8);
        return "redirect:/documents?page=0&find=" + finded;
    }

    @GetMapping("/sort/search/reset")
    public String resetSearch() {
        sortModel.setFind(null);
        return "redirect:/documents/sort";
    }

    @GetMapping("/sort")
    public String sort(@ModelAttribute("sortModel") SortModel sortModel) {
        if (sortModel.getFind() != null) {
            sortModel.setFind(URLEncoder.encode(sortModel.getFind(), StandardCharsets.UTF_8));
            return "redirect:/documents?" + sortModel.getUrl() + "&find=" + sortModel.getFind();
        }
        return "redirect:/documents?" + sortModel.getUrl();
    }

    @PostMapping("/upload")
    public String save(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/documents";
        }
        propertyDocumentService.save(file, file.getOriginalFilename());
        return "redirect:/documents";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") int id) {
        propertyDocumentService.delete(id);
        return "redirect:/documents/sort";
    }

    @ModelAttribute("sortModel")
    public SortModel getSortModelKey() {
        return sortModel;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("newFile", propertyDocumentService.get(id).getFileSourse());
        model.addAttribute("document", propertyDocumentService.get(id));
        return "documents/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("document") PropertyDocument propertyDocument) {
        propertyDocumentService.update(id, propertyDocument);
        return "redirect:/documents/" + id + "/edit";
    }
}