package lk.ijse.thogakade.dto;

public class itemDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int QTYOnHand;

    public itemDTO(String code, String description, double unitPrice, int QTYOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.QTYOnHand = QTYOnHand;
    }

    public itemDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQTYOnHand() {
        return QTYOnHand;
    }

    public void setQTYOnHand(int QTYOnHand) {
        this.QTYOnHand = QTYOnHand;
    }

    @Override
    public String toString() {
        return "itemDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", QTYOnHand=" + QTYOnHand +
                '}';
    }
}
