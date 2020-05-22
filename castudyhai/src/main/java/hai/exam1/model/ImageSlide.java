package hai.exam1.model;

import javax.persistence.*;

@Entity
@Table(name = "imageslides")
public class ImageSlide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ImageSlide() {
    }

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
