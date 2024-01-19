package simonedangelo.mondovan.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.ServiceStatus.Payload.ServicesStatusDTO;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Type;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesServicesStatusDTO;
import simonedangelo.mondovan.Vehicle.VehiclesRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultsService {
    @Autowired
    VehiclesRepository vehiclesRepository;

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateOnly(LocalDate start, LocalDate end) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndMoney(LocalDate start, LocalDate end, int money) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndMoneyAndSupply(LocalDate start, LocalDate end, int money, Supply supply) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndMoneyAndSupplyAndType(LocalDate start, LocalDate end, int money, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply) && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndMoneyAndType(LocalDate start, LocalDate end, int money, Type type) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndSupply(LocalDate start, LocalDate end, Supply supply) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndType(LocalDate start, LocalDate end, Type type) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndSupplyAndType(LocalDate start, LocalDate end, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type) && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndProvince(LocalDate start, LocalDate end, String province) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndProvAndSupply(LocalDate start, LocalDate end, String province, Supply supply) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.supply().equals(supply)).toList();
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndProvAndType(LocalDate start, LocalDate end, String province, Type type) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type)).toList();
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndProvAndSupplyAndType(LocalDate start, LocalDate end, String province, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type) && x.supply().equals(supply)).toList();
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndProvinceAndPrice(LocalDate start, LocalDate end, String province, int money) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndPriceAndSupply(LocalDate start, LocalDate end, String province, int money, Supply supply) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndPriceAndType(LocalDate start, LocalDate end, String province, int money, Type type) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndPriceAndSupplyAndType(LocalDate start, LocalDate end, String province, int money, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply) && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndBeds(LocalDate start, LocalDate end, int beds) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndBedsAndSupply(LocalDate start, LocalDate end, int beds, Supply supply) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndBedsAndType(LocalDate start, LocalDate end, int beds, Type type) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndBedsAndSupplyAndType(LocalDate start, LocalDate end, int beds, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type) && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndBedsAndPrice(LocalDate start, LocalDate end, int beds, int money) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByBedsAndPriceAndSupply(LocalDate start, LocalDate end, int beds, int money, Supply supply) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndType(LocalDate start, LocalDate end, int beds, int money, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply) && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByBedsAndPriceAndType(LocalDate start, LocalDate end, int beds, int money, Type type) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndBeds(LocalDate start, LocalDate end, int beds, String province) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndBedsAndSupply(LocalDate start, LocalDate end, int beds, String province, Supply supply) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndBedsAndType(LocalDate start, LocalDate end, int beds, String province, Type type) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndBedsAndSupplyAndType(LocalDate start, LocalDate end, int beds, String province, Supply supply, Type type) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.type().equals(type) && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndBedsAndPrice(LocalDate start, LocalDate end, int beds, String province, int money) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByBedsAndPriceAndSupply(LocalDate start, LocalDate end, int beds, int money, Supply supply, String prov) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, prov).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, prov).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndType(LocalDate start, LocalDate end, int beds, int money, Supply supply, Type type, String prov) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, prov).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, prov).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.supply().equals(supply) && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByBedsAndPriceAndType(LocalDate start, LocalDate end, int beds, int money, Type type, String prov) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, prov).isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, prov).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getSits(), vehicle.getVehiclesArrangement().getBeds(), vehicle.getPricePerDay(), vehicle.getSupply(), vehicle.getType(), vehicle.getAvatar(), vehicle.getOwner().getAddressesOwner().getTown().getProvince().getName(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        List<VehiclesServicesStatusDTO> vehicleFilter = collectionVehicleAvailable.stream().filter(x -> x.pricePerDay() <= money && x.type().equals(type)).toList();
        if (vehicleFilter.isEmpty()) {
            throw new NotFoundEx("Avaiability not found");
        } else {
            return vehicleFilter;
        }
    }

}
