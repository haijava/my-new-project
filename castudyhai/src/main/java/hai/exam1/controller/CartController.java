package hai.exam1.controller;

import hai.exam1.model.*;
import hai.exam1.service.*;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ReceiptItemService receiptItemService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/cart-home")
    public String cartPage() {

        return "/cart/cart";
    }

    @ModelAttribute("myCartItems")
    private Cart cart() {
        return new Cart();
    }

    @GetMapping("/buy/{id}")
    public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("id") Long productId) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        Product product = productService.findId(productId);
        if (product != null) {
            if (cartItems.containsKey(productId)) {
                Cart item = cartItems.get(productId);
                // item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);

                cartItems.put(productId, item);
            } else {
                Cart item = new Cart();
                item.setProduct(product);
                item.setQuantity(item.getQuantity());
                /*item.setQuantity(quantity);*/
                cartItems.put(productId, item);
            }
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("total", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        mm.addAttribute("total", totalPrice(cartItems));
        mm.addAttribute("session", session);
//        mm.addAttribute("receipt", new Receipt());
        mm.addAttribute("categories", categoryService.findAll());
        return "/cart/cart";
    }
   /* @Autowired
    private JavaMailSender mailSender;
    public void sendMail(Receipt receipt){
        SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
        crunchifyMsg.setFrom("xuanhai.freezone@gmail.com");
        crunchifyMsg.setTo("cashier1.freezone@gmail.com");
        crunchifyMsg.setSubject("INFORMATION CUSTOMER");
        crunchifyMsg.setText(receipt.getReceiptMail());
        mailSender.send(crunchifyMsg);
    }*/

    public class Constants {


        public static final String MY_EMAIL = "xuanhai.freezone@gmail.com";

        public static final String MY_PASSWORD = "freezonehue2";

        public static final String FRIEND_EMAIL = "cashier1.freezone@gmail.com";
    }

    public void sendMailhtml( Receipt receipt) {

        try {
            HttpSession session;
            // Tạo đối tượng Email
            HtmlEmail email = new HtmlEmail();

            // Cấu hình
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(
                    Constants.MY_EMAIL, Constants.MY_PASSWORD));
            email.setSSLOnConnect(true);
            email.setFrom(Constants.MY_EMAIL, "CUSTOMER");

            // Người nhận
            email.addTo(Constants.FRIEND_EMAIL);

            // Tiêu đề
            email.setSubject("KHÁCH HÀNG ĐẶT MUA HÀNG TẠI HAITECH!!!");

            // Nhúng image và lấy ra ID của nội dung (Content-ID)
           /* URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
            String cid = email.embed(url, "Apache logo");*/
            String hl = receipt.getReceiptName();
            String h2 = receipt.getPhone();
            String h3 = receipt.getReceiptAddress();
            String h4 = receipt.getReceiptMail();
            Long h5 = receipt.getReceiptId();


            // Sét nội dung email định dạng HTML.
            email.setHtmlMsg("<html><head><style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "}\n" +
                    "</style> </head>" +

                    "<h1>INFORMATION CUSTOMER  </h1> " +
                  "<table style='>" +
                    "<tr>" +
                    "<td>" +
                    "CODE_ID: " + h5 +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "NAME: " + hl +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "PHONE: " + h2 +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "MAIL: " + h4 +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "ADDRESS: " + h3 +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</html>");

            // Thiết lập các thông báo thay thế
            // (Trong trường hợp chương trình đọc mail của người nhận ko hỗ trợ đọc HTML Email)
            email.setTextMsg("Your email client does not support HTML messages");

            // Gửi email
            email.send();

            System.out.println("Sent!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/quantity/{operator}/{id}")
    public String quantity(@PathVariable String operator, @PathVariable Long id, HttpSession session, Model model) {
        /* List<Cart> itemList = (List<Cart>) session.getAttribute("myCartItems");*/

        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");

        int quantity = 1;
        long total = 0;
        Cart cart = new Cart();

        for (Map.Entry<Long, Cart> entry : cartItems.entrySet()) {
            cart = entry.getValue();
            if (id.equals(cart.getProduct().getId())) {
                if (operator.equals("add")) {
                    cart.setQuantity(cart.getQuantity() + 1);
                } else {
                    if (cart.getQuantity() != 1) {
                        cart.setQuantity(cart.getQuantity() - quantity);
                    }
                }
            }

        }

        for (Map.Entry<Long, Cart> entry : cartItems.entrySet()) {
            total += entry.getValue().getProduct().getPriceSale() * entry.getValue().getQuantity();
        }
        session.setAttribute("myCartItems", cartItems);
        model.addAttribute("myCartItems", cartItems);
        session.setAttribute("total", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        String totalView = String.valueOf(totalPrice(cartItems));
        model.addAttribute("total", totalView);
        return "/cart/cart";
    }

    @GetMapping("/update-cart")
    public String viewUpdate(@RequestParam("quantity") Long quantity,Model mm, HttpSession session) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        session.setAttribute("myCartItems", cartItems);
        mm.addAttribute("myCartItems", cartItems);
        mm.addAttribute("session", session);
        return "/cart/order-info";
    }
    @GetMapping("/remove-cart/{id}")
    public String viewRemove(ModelMap mm, HttpSession session, @PathVariable("id") Long productId) {
        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (cartItems.containsKey(productId)) {
            cartItems.remove(productId);
        }
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("total", totalPrice(cartItems));
        mm.addAttribute("total", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "/cart/cart";
    }

    public double totalPrice(HashMap<Long, Cart> cartItems) {
        int count = 0;
        for (Map.Entry<Long, Cart> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPriceSale() * list.getValue().getQuantity();
        }
        return count;
    }

    @GetMapping("/checkout")
    public String showFormOrder(@ModelAttribute("session") String cartInfo, Model model) {
        model.addAttribute("cartInfo", cartInfo);
        model.addAttribute("receipt", new Receipt());
        model.addAttribute("customer", new Customer());
        model.addAttribute("categories", categoryService.findAll());

        return "/cart/order-info";
    }


    @PostMapping("/checkout")
    public String viewCheckout(ModelMap mm, HttpSession session, @ModelAttribute("receipt") Receipt receipt, @ModelAttribute("customer")Customer customer) {

        HashMap<Long, Cart> cartItems = (HashMap<Long, Cart>) session.getAttribute("myCartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        receipt.setReceiptDate(LocalDateTime.now());
        receipt.setReceiptStatus(true);
        receiptService.save(receipt);
        customerService.save(customer);

        for (Map.Entry<Long, Cart> entry : cartItems.entrySet()) {
            ReceiptItem receiptItem = new ReceiptItem();
            receiptItem.setReceipt(receipt);
            receiptItem.setProduct(entry.getValue().getProduct());
            receiptItem.setPrice(entry.getValue().getProduct().getPrice());
            receiptItem.setQuantity((long) entry.getValue().getQuantity());
            receiptItem.setReceiptItemStatus(true);
            receiptItemService.save(receiptItem);
        }
        /* sendMail(receipt);*/
        sendMailhtml(receipt);
        cartItems = new HashMap<>();
        session.setAttribute("myCartItems", cartItems);
        session.setAttribute("total", totalPrice(cartItems));
        mm.addAttribute("total", totalPrice(cartItems));
        session.setAttribute("myCartNum", cartItems.size());
        return "/cart/order-success";
    }

}
