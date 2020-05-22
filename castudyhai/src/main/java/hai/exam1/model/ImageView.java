package hai.exam1.model;

import javax.persistence.*;

@Entity
@Table(name = "imageviews")
public class ImageView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageviews() {
        return imageviews;
    }

    public void setImageviews(String imageviews) {
        this.imageviews = imageviews;
    }
}
