package simonedangelo.mondovan.Vehicle.Payload;

import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.Vehicle.Enum.License;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Transmission;
import simonedangelo.mondovan.Vehicle.Enum.Type;

import java.time.LocalDate;
import java.util.List;

public record VehiclesServicesStatusDTO(String name,
                                        String model,
                                        String brand,
                                        List<String> avatar,
                                        Supply supply,
                                        LocalDate firstEnrollment,
                                        License license,
                                        Type type,
                                        Transmission transmission,
                                        int displacement,
                                        long kilometers,
                                        double height,
                                        double width,
                                        double length,
                                        long idServicesState,
                                        LocalDate date,
                                        Status state) {
}
