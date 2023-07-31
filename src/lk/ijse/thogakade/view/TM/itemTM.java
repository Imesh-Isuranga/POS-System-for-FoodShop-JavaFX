package lk.ijse.thogakade.view.TM;


import javafx.scene.control.Button;

public class itemTM {
    private String code;
    private String description;
    private double unitPrice;
    private int QTYOnHand;
    private Button btn;

    public itemTM(String code, String description, double unitPrice, int QTY, Button btn) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.QTYOnHand = QTY;
        this.btn = btn;
    }

    public itemTM() {
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "itemTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", QTY=" + QTYOnHand +
                ", btn=" + btn +
                '}';
    }
}
