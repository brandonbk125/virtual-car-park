import java.util.Objects;

public class Vehicle {
    private String reg;
    private int height;
    private int length;

    public  Vehicle(){

    }

    public Vehicle(String reg, int height, int length){
        this.reg = reg;
        this.height = height;
        this.length = length;

    }

    public void setReg(String reg){ this.reg = reg;}

    public String getReg(){
        return reg;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public void setLength(int length){
        this.length = length;
    }

    public int getLength(){
        return length;
    }


    @Override
    public boolean equals(Object o) {
        Vehicle vehicle = (Vehicle) o;
        if (this.reg == vehicle.reg) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return Objects.equals(reg, vehicle.reg);
    }
}
