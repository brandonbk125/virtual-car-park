public class longerVehicle implements Vehicle {

    private String reg;

    /**
     * A longer vehicle
     */

    public longerVehicle() {

    }



    public longerVehicle(String reg) {
        this.reg = reg;
    }

    /**
     *
     * @return registration of the longer vehicle
     */

    @Override
    public String getReg() {
        return reg;
    }

    /**
     *
     * @param reg registration of the longer vehicle
     */
    @Override
    public void setReg(String reg) {
        this.reg = reg;
    }

    @Override
    public String toString(){
        return "Longer Vehicle, Reg: "+ reg;
    }


}
