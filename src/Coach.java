public class Coach implements Vehicle{

    private String reg;

    /**
     * A coach
     */

    public Coach(){

    }

    public Coach(String reg){
        this.reg = reg;
    }

    /**
     *
     * @return registration of the coach
     */
    @Override
    public String getReg(){
        return reg;
    }

    /**
     *
     * @param reg registration of the coach
     */
    @Override
    public void setReg(String reg){
        this.reg = reg;
    }

    @Override
    public String toString(){
        return "Coach, Reg: "+ reg;
    }

}
