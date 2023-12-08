package simonedangelo.mondovan.Vehicle.Payload;

import simonedangelo.mondovan.ServiceStatus.Payload.ServicesStatusDTO;

import java.util.List;

public record VehiclesServicesStatusDTO(long id,
                                        String name,
                                        String model,
                                        String brand,
                                        String desc,
                                        List<String> avatar,
                                        List<ServicesStatusDTO> listStatus) {
}
