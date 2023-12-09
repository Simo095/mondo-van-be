package simonedangelo.mondovan.Vehicle.Payload;

import simonedangelo.mondovan.Vehicle.Enum.License;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Transmission;
import simonedangelo.mondovan.Vehicle.Enum.Type;

import java.time.LocalDate;

public record VehiclesDTO(String name,
                          String model,
                          String brand,

                          Supply supply,
                          LocalDate firstEnrollment,
                          License license,
                          Type type,
                          Transmission transmission,
                          int displacement,
                          int sits,
                          long kilometers,
                          double height,
                          double width,
                          double length,
                          String shortDescriptions,
                          double pricePerDay) {
}
