package lk.ijse.thogakade.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class item extends SuperEntity{
    @Id
    private String code;
    private String description;
    private double unitPrice;
    private int QTYOnHand;

    public item(String code, String description, double unitPrice, int QTYOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.QTYOnHand = QTYOnHand;
    }

    public item() {
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
        return "item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", QTYOnHand=" + QTYOnHand +
                '}';
    }
}
