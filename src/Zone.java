import java.util.ArrayList;

public class Zone {
    private final int CAPACITY = 3;
    private ArrayList<Vehicle> vehicles;

    public Zone(){
        vehicles = new ArrayList<>(CAPACITY);

    }
    public ArrayList<Vehicle> getVehicles(){
        return vehicles;
    }

    public Vehicle findVehicle(Vehicle o){
        Vehicle v = null;

        if (vehicles.contains(o)){
            v = o;
        }

        return v;
    }

}
