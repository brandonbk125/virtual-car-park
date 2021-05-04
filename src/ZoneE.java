import java.util.ArrayList;

public class ZoneE implements Zone{
    private int capacity;
    private ArrayList<String> vehicles = new ArrayList<>();

    public ZoneE() {

    }

    public ZoneE(int capacity) {
        this.capacity = capacity;
        vehicles = new ArrayList<>(capacity);
    }
    @Override
    public void addVehicle(Vehicle v) {
        if ((v.getClass().isInstance(new Motorbike()))) {
            vehicles.add("M" + v.getReg());
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
