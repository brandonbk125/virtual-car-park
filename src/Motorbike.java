public class Motorbike implements Vehicle {

    private String reg;

    /**
     * A motorbike
     */

    public Motorbike() {

    }

    public Motorbike(String reg) {
        this.reg = reg;
    }

    /**
     *
     * @return registration of the motorbike
     */

    @Override
    public String getReg() {
        return reg;
    }

    /**
     *
     * @param reg registration of the motorbike
     */

    @Override
    public void setReg(String reg) {
        this.reg = reg;
    }

    @Override
    public String toString(){
        return "Motorbike, Reg: "+ reg;
    }

}
