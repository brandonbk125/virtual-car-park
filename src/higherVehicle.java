public class higherVehicle implements Vehicle {
    private String reg;

    /**
     * A higher vehicle
     */


    public higherVehicle() {

    }


    public higherVehicle(String reg) {
        this.reg = reg;
    }


    /**
     *
     * @return registration of the higher vehicle
     */
    @Override
    public String getReg() {
        return reg;
    }

    /**
     *
     * @param reg registration of the higher vehicle
     */
    @Override
    public void setReg(String reg) {
        this.reg = reg;
    }

    @Override
    public String toString(){
        return "Higher Vehicle, Reg: "+ reg;
    }


}
