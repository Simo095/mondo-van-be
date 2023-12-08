package simonedangelo.mondovan.ServiceStatus.Payload;

import simonedangelo.mondovan.ServiceStatus.Enum.Status;

import java.time.LocalDate;

public record ServicesStatusDTO(long idServiceStatus, LocalDate date, Status state) {
}
