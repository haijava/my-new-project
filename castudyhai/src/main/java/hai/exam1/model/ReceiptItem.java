package hai.exam1.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "receiptItems")
public class ReceiptItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long receiptItemId;

    private Long priceItem;
    private Long quantityItem;
    private boolean receiptItemStatus;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public Long getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Long priceItem) {
        this.priceItem = priceItem;
    }

    public Long getQuantityItem() {
        return quantityItem;
    }

    public void setQuantityItem(Long quantityItem) {
        this.quantityItem = quantityItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isReceiptItemStatus() {
        return receiptItemStatus;
    }

    public void setReceiptItemStatus(boolean receiptItemStatus) {
        this.receiptItemStatus = receiptItemStatus;
    }

    public ReceiptItem() {
    }

    public Long getReceiptItemId() {
        return receiptItemId;
    }

    public void setReceiptItemId(Long receiptItemId) {
        this.receiptItemId = receiptItemId;
    }

    public Long getPrice() {
        return priceItem;
    }

    public void setPrice(Long price) {
        this.priceItem = price;
    }

    public Long getQuantity() {
        return quantityItem;
    }

    public void setQuantity(Long quantity) {
        this.quantityItem = quantity;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }




}
