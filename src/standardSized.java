public class standardSized implements Vehicle {
    private String reg;

    /**
     * A standard sized vehicle
     */

    public standardSized() {

    }

    public standardSized(String reg) {
        this.reg = reg;
    }


    /**
     *
     * @return registration of the standard sized vehicle
     */
    @Override
    public String getReg() {
        return reg;
    }

    /**
     *
     * @param reg registration of the standard sized vehicle
     */
    @Override
    public void setReg(String reg) {
        this.reg = reg;
    }



    @Override
    public String toString(){
        return "Standard Sized Vehicle, Reg: " + reg;
    }


}
