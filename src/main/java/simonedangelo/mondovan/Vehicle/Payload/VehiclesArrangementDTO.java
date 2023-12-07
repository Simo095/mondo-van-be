package simonedangelo.mondovan.Vehicle.Payload;

public record VehiclesArrangementDTO(
        int bads,
        String descriptionBeds,
        boolean bathroom,
        boolean hotWater,
        boolean water,
        boolean wc,

        boolean kitchen,
        boolean fridge,
        boolean gas,
        String descriptionKitchen,
        String accessoriesDescription,
        String descriptionBathroom,
        boolean doItMySelf
) {
}
