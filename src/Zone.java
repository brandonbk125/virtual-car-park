public abstract interface Zone {
    /**
     * The interface to all Zones
     */

    /**
     * The method to add a vehicle to a zone
     * @param v vehicle to add
     */
    public abstract void addVehicle(Vehicle v);

    /**
     *
     * @return if the zone has any space left
     */

    public boolean hasSpace();

    /**
     *
     * @return the space id of the next available zone
     */
    public int getNextSpaceNumber();

    /**
     *
     * @param space the index of the space to be cleared
     */
    public void clearSpace(int space);



}
