package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import com.bikemaintapp.Bike.Maintenance.App.storage.FileSystemStorageService;
import com.bikemaintapp.Bike.Maintenance.App.storage.StorageFileNotFoundException;
import com.bikemaintapp.Bike.Maintenance.App.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("upload")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    UserDao userDao;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {

        //Check to ensure file is an image, redirect and give message if not
        if (!file.getContentType().contains("image") ) {

            redirectAttributes.addFlashAttribute("message",
                    "You can not upload files that aren't an image");
            return "redirect:/upload/";
        }

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/upload/";
    }

    //TODO get this working to upload image for users
    @PostMapping("/userImage")
    public String userImage(Model model, @RequestParam("file")MultipartFile file,
                            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String fileName = "user" + user.getId();

        //Check to ensure file is an image, redirect and give message if not
        if (!file.getContentType().contains("image") ) {

            redirectAttributes.addFlashAttribute("message",
                    "You can not upload files that aren't an image");
            return "redirect:/upload/";
        }

        storageService.storeName(file, fileName);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        user.setImage("/images/" + fileName);
        userDao.save(user);

        model.addAttribute("user", user);
        model.addAttribute("title", "Edit Account: " + user.getName());
        return "redirect:/user/edit";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
