package se.lexicon.model;

/**
 * Represents a parking spot within a parking lot.
 */
public class ParkingSpot {

    private Integer spotNumber; // Unique identifier for the parking spot
    private boolean occupied;  // Indicates whether the parking spot is occupied (true) or available (false)
    private Integer areaCode;  // Identifier for the area where the parking spot is located

    public ParkingSpot(Integer spotNumber, Integer areaCode) {
        this.spotNumber = spotNumber;
        this.areaCode = areaCode;
    }

    /**
     * Constructor to initialize a parking spot with all attributes.
     *
     * @param spotNumber The unique spot number.
     * @param occupied   The occupancy status of the parking spot.
     * @param areaCode   The area code where the parking spot is located.
     */
    public ParkingSpot(Integer spotNumber, boolean occupied, Integer areaCode) {
        this(spotNumber, areaCode);
        this.occupied = occupied;
    }

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Marks the parking spot as occupied.
     * This method sets the `occupied` field to true.
     */
    public void occupy() {
        occupied = true;
    }

    /**
     * Marks the parking spot as vacant.
     * This method sets the `occupied` field to false.
     */
    public void vacate() {
        occupied = false;
    }

    /**
     * Provides a string representation of the ParkingSpot object.
     *
     * @return A string with spot number, occupancy status, and area code.
     */
    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", occupied=" + occupied +
                ", areaCode=" + areaCode +
                '}';
    }
}
