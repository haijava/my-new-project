package hai.exam1.model;



import java.time.LocalDateTime;

public class Stastical {
    private Long id;
    private LocalDateTime month;
    private Long numberReceipt;

    public Stastical() {
    }

    public Stastical(LocalDateTime month, Long numberReceipt) {
        this.month = month;
        this.numberReceipt = numberReceipt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getMonth() {
        return month;
    }

    public void setMonth(LocalDateTime month) {
        this.month = month;
    }

    public Long getNumberReceipt() {
        return numberReceipt;
    }

    public void setNumberReceipt(Long numberReceipt) {
        this.numberReceipt = numberReceipt;
    }
}
