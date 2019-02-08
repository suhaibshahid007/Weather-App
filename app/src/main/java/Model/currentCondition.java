package Model;

/**
 * Created by Muhammad Suhaib on 8/10/2018.
 */

public class currentCondition {

    private int weaterId;
    private String condition;
    private String description;
    private int pressure;
    private int humidity;
    private double temp;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public  double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public int getPressure()
    {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    public int getWeaterId() {
        return weaterId;
    }
    public void setWeaterId(int weaterId) {
        this.weaterId = weaterId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
