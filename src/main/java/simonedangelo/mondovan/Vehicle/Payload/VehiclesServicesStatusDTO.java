package simonedangelo.mondovan.Vehicle.Payload;

import simonedangelo.mondovan.ServiceStatus.Payload.ServicesStatusDTO;

import java.util.List;

public record VehiclesServicesStatusDTO(String name,
                                        String model,
                                        String brand,
                                        List<String> avatar,
                                        List<ServicesStatusDTO> listStatus) {
}
