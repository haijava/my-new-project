package hai.exam1.controller;

import hai.exam1.model.*;
import hai.exam1.service.ImageViewServices;
import hai.exam1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageViewController {
    @Autowired
    private ImageViewServices imageViewServices;
    @Autowired
    private ProductService productService;
    @ModelAttribute("product")
    public Iterable<Product> products() {
        return productService.findAll();
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == ProductUpload.class) {
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
    @GetMapping("/image")
    public ModelAndView showImages() {
        Iterable<ImageView> imageViews = imageViewServices.findAll();
        ModelAndView modelAndView = new ModelAndView("/imageview/viewImage");
        modelAndView.addObject("imageViews", imageViews);
        return modelAndView;
    }


    @GetMapping("/create-imageView")
    public ModelAndView showFormProduct() {
        ModelAndView modelAndView = new ModelAndView("/imageview/createimage");
        modelAndView.addObject("imageViewUpload", new ImageViewUpload());
        modelAndView.addObject("imageView", new ImageView());
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @PostMapping("/create-imageView")
    public ModelAndView create(HttpServletRequest request, @ModelAttribute("imageView") ImageView imageView, @ModelAttribute("imageViewUpload") ImageViewUpload imageViewUpload) {
        String uploadRootPath = request.getServletContext().getRealPath("images");
        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);

        String hardUploadFolder = "C:\\castudymodule2\\apache-tomcat-9.0.27\\webapps\\ROOT\\images";
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = imageViewUpload.getFileDatas();
        Map<File, String> uploadedFiles = new HashMap<>();
        for (CommonsMultipartFile fileData : fileDatas) {
            String name = fileData.getOriginalFilename();
//            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
//                    imageViewUpload.setImageview("/images/" + name);
//                    //
                    File serverHardFile = new File(hardUploadFolder + File.separator + name);
                    stream = new BufferedOutputStream(new FileOutputStream(serverHardFile));
                    stream.write(fileData.getBytes());
                    stream.close();
//                    //
                    uploadedFiles.put(serverFile, name);
                    imageView.setImageviews(name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        imageViewServices.save(imageView);
        ModelAndView modelAndView = new ModelAndView("/imageview/createimage");
        modelAndView.addObject("imageUpload", new ImageViewUpload());
        modelAndView.addObject("message", "a new image was create");
        return modelAndView;
    }


}
