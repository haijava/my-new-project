package hai.exam1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long price;
    private Long priceSale;
    private String image;
    private String description;

    @OneToOne(mappedBy = "product")
    private ImageSlide imageSlide;

    @OneToMany(mappedBy = "product")
    Set<ImageView> imageViews;

    @OneToMany(mappedBy = "product")
    Set<ReceiptItem> receiptItems;
    @Transient
    private CommonsMultipartFile[] fileDatas;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Set<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(Set<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

    public Set<ImageView> getImageViews() {
        return imageViews;
    }

    public void setImageViews(Set<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    public ImageSlide getImageSlide() {
        return imageSlide;
    }

    public void setImageSlide(ImageSlide imageSlide) {
        this.imageSlide = imageSlide;
    }

    public CommonsMultipartFile[] getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(CommonsMultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long productId) {
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Long getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Long priceSale) {
        this.priceSale = priceSale;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
