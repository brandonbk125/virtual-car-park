import java.util.ArrayList;

public class ZoneC implements Zone{
    private ArrayList<String> vehicles = new ArrayList<>();
    private int capacity;

    public ZoneC() {


    }

    public ZoneC(int capacity) {
        this.capacity = capacity;
        vehicles = new ArrayList<>(capacity);
    }

    @Override
    public void addVehicle(Vehicle v) {
        if ((v.getClass().isInstance(new Coach()))) {
            vehicles.add("C" + v.getReg());
            System.out.println("Vehicle added");
        } else {
            System.out.println("Vehicle not appropriate for this zone");
        }
    }

    @Override
    public boolean hasSpace() {
        return (vehicles.size() < capacity);
    }

    @Override
    public int getNextSpaceNumber(){
        return vehicles.size()+1;
    }

    @Override
    public void clearSpace(int space){
        vehicles.remove(space-1);

    }

    @Override
    public String toString() {
        String result = "";
        for (String v:vehicles){
            result+=v;
            result+=",";
        }
        return result;
    }
}
