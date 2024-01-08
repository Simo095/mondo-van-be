package simonedangelo.mondovan.Vehicle.Payload;

import simonedangelo.mondovan.ServiceStatus.Payload.ServicesStatusDTO;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Type;

import java.util.List;

public record VehiclesServicesStatusDTO(long id,
                                        String name,
                                        String model,
                                        String brand,
                                        String desc,
                                        int sits,
                                        int beds,
                                        double pricePerDay,
                                        Supply supply,
                                        Type type,
                                        List<String> avatar,
                                        String province,
                                        List<ServicesStatusDTO> listStatus) {
}
