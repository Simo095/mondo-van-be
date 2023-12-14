package simonedangelo.mondovan.Vehicle.Payload;

import simonedangelo.mondovan.ServiceStatus.Payload.ServicesStatusDTO;

import java.util.List;

public record VehiclesServicesStatusDTO(long id,
                                        String name,
                                        String model,
                                        String brand,
                                        String desc,
                                        int sits,
                                        double pricePerDay,
                                        List<String> avatar,
                                        String province,
                                        List<ServicesStatusDTO> listStatus) {
}
