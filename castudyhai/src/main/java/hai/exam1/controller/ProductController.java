package hai.exam1.controller;

import hai.exam1.model.*;
import hai.exam1.service.CategoryService;
import hai.exam1.service.ImageViewServices;
import hai.exam1.service.ProductService;
import hai.exam1.service.StatusService;
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
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageViewServices imageViewServices;
    @Autowired
    private StatusService statusService;

    @ModelAttribute("category")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("statuses")
    public Iterable<Status> statuses() {
        return statusService.findAll();
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

    @GetMapping("/")
    public ModelAndView showIndex(@RequestParam("s") Optional<String> s) {
        Iterable<Product> products;
        if (s.isPresent()) {
            products = productService.findAllByNameContains(s.get());
        } else {
            products = productService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("/product/index");

        modelAndView.addObject("products", products);


        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("statuses",statusService.findAll());
        return modelAndView;
    }

    @GetMapping("/price/{p}")
    public ModelAndView showPriceIndex(@PathVariable("p") String p) {
        ModelAndView modelAndView = new ModelAndView("/product/index");
        switch (p) {
            case "1":
                modelAndView.addObject("products", productService.listProduct_3milion_6milion());
                break;
            case "2":
                modelAndView.addObject("products", productService.listProduct_6milion_9milion());
                break;
            case "3":
                modelAndView.addObject("products", productService.listProduct_9milion_22milion());
            default:
                System.out.println("a");
        }
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView cartIndex() {
        Iterable<Product> products = productService.findAll();

        ModelAndView modelAndView = new ModelAndView("/login/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView showAdmin() {
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/admin");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }
    @GetMapping("/admin/{categoryId}")
    public ModelAndView showAdminCategories(@PathVariable("categoryId") Long categoryId) {
       /* Iterable<Product> productss = productService.findAll();*/
        Iterable<Product> products = productService.findByCategory(categoryService.findId(categoryId));
        ModelAndView modelAndView = new ModelAndView("/admin/admin");
        /*modelAndView.addObject("productss", productss);*/
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/categories/{categoryId}")
    public ModelAndView showListProduct(@PathVariable("categoryId") Long categoryId) {
        Iterable<Product> products = productService.findByCategory(categoryService.findId(categoryId));
        ModelAndView modelAndView = new ModelAndView("/product/categories");
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("id", categoryId);
        return modelAndView;

    }

    @GetMapping("/create-product")
    public ModelAndView showFormProduct(@ModelAttribute("product") ProductUpload product) {
        ModelAndView modelAndView = new ModelAndView("/admin/create");
        modelAndView.addObject("product", new ProductUpload());
        modelAndView.addObject("productUpload", product);
        modelAndView.addObject("statuses",statusService.findAll());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView create(HttpServletRequest request, @ModelAttribute("product") ProductUpload product) {
        Product productPersist = new Product();
        productPersist.setName(product.getName());
        productPersist.setImage(product.getImage());
        productPersist.setPrice(product.getPrice());
        productPersist.setPriceSale(product.getPriceSale());
        productPersist.setDescription(product.getDescription());
        productPersist.setStatus(product.getStatus());
        productPersist.setCategory(product.getCategory());
        String uploadRootPath = request.getServletContext().getRealPath("images");
        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);

        String hardUploadFolder = "C:\\castudymodule2\\apache-tomcat-9.0.27\\webapps\\ROOT\\images";
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = product.getFileDatas();
        Map<File, String> uploadedFiles = new HashMap<>();
        for (CommonsMultipartFile fileData : fileDatas) {
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    product.setImage("/images/" + name);
//                    //
                    File serverHardFile = new File(hardUploadFolder + File.separator + name);
                    stream = new BufferedOutputStream(new FileOutputStream(serverHardFile));
                    stream.write(fileData.getBytes());
                    stream.close();
//                    //
                    uploadedFiles.put(serverFile, name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        productService.save(productPersist);


        ModelAndView modelAndView = new ModelAndView("/admin/create");
        modelAndView.addObject("productUpload", new ProductUpload());
        modelAndView.addObject("message", "a new product was create");
        return modelAndView;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        Product product = productService.findId(id);
        ModelAndView modelAndView = new ModelAndView("/admin/edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute("product") Product product, ProductUpload productUpload) {
        product.setName(productUpload.getName());
        product.setImage(productUpload.getImage());
        product.setStatus(productUpload.getStatus());
        product.setDescription(productUpload.getDescription());
        product.setPrice(productUpload.getPrice());
        product.setPriceSale(productUpload.getPriceSale());
        product.setCategory(productUpload.getCategory());
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/admin/edit");
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable("id") Long id) {
        Product product = productService.findId(id);
        ModelAndView modelAndView = new ModelAndView("/admin/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("product") Product product) {
        productService.delete(product.getId());

        return "redirect:/admin";
    }

    @GetMapping("/viewp/{id}/{price}")
    public ModelAndView viewpro(@PathVariable("id") Long id, @PathVariable("price") Long price) {
        Product product = productService.findId(id);
        Iterable<Product> products = productService.listProduct_price(price, price);
        ModelAndView modelAndView = new ModelAndView("/product/viewProduct");
        modelAndView.addObject("products", products);
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", categoryService.findAll());
        Iterable<ImageView> imageViews = imageViewServices.findAllByProduct(product);
        modelAndView.addObject("imageViews", imageViews);
        /* modelAndView.addObject("id",id);*/
        return modelAndView;
    }

    @GetMapping("/price/{price}/{id}")
    public ModelAndView showPrice(@PathVariable("id") Long id, @PathVariable("price") String price) {
        ModelAndView modelAndView = new ModelAndView("/product/categories");
        switch (price) {
            case "price_3milion_6milion":
                modelAndView.addObject("products", productService.listProduct_price_3milion_6milion(id));
                break;
            case "price_6milion_9milion":
                modelAndView.addObject("products", productService.listProduct_price_6milion_9milion(id));
                break;
            case "price_9milion_22milion":
                modelAndView.addObject("products", productService.listProduct_price_9milion_22milion(id
                ));
                break;
            default:
                System.out.println("again");
        }
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/name")
    public ModelAndView search(@RequestParam("s") Optional<String> s, @RequestParam("id") Long id) {
        Iterable<Product> products;
        ModelAndView modelAndView = new ModelAndView("/product/categories");
        if (s.isPresent()) {
            products = productService.findAllByNameContains(s.get());
            modelAndView.addObject("products", productService.listProduct_name(id, "%" + s.get() + "%"));
        } else {
            products = productService.findAll();
        }
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("id", id);
        return modelAndView;
    }

}
