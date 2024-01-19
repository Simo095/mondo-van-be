package simonedangelo.mondovan.Vehicle.Arrangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesArrangementDTO;
import simonedangelo.mondovan.Vehicle.Vehicle;
import simonedangelo.mondovan.Vehicle.VehiclesRepository;

import java.io.IOException;

@Service
public class VehiclesArrangementService {
    @Autowired
    private VehiclesArrangementRepository vehiclesArrangementRepository;
    @Autowired
    private VehiclesRepository vehiclesRepository;

    public Vehicle getVehicleByIdOwner(long idOwner) throws NotFoundEx {
        return vehiclesRepository.findByIdOwner(idOwner).orElseThrow(
                () -> new BadRequestEx("this user has no vehicles assigned"));
    }

    public VehiclesArrangement getVehicleArrangementByIdOwner(long idOwner) throws NotFoundEx {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        return vehiclesArrangementRepository.findByIdVehicles(v.getId()).orElseThrow(
                () -> new NotFoundEx("there are no arrangement")
        );
    }

    public Page<VehiclesArrangement> getVehicleArrangement(int page, int size, String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return vehiclesArrangementRepository.findAll(p);
    }

    public Vehicle saveVehiclesArrangement(long idOwner, VehiclesArrangementDTO objArrangement) throws IOException {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        VehiclesArrangement vA = new VehiclesArrangement();
        vA.setBeds(objArrangement.bads());
        vA.setDescriptionBeds(objArrangement.descriptionBeds());
        vA.setBathroom(objArrangement.bathroom());
        vA.setWater(objArrangement.water());
        vA.setHotWater(objArrangement.hotWater());
        vA.setKitchen(objArrangement.kitchen());
        vA.setFridge(objArrangement.fridge());
        vA.setGas(objArrangement.gas());
        vA.setDescriptionBathroom(objArrangement.descriptionBathroom());
        vA.setDescriptionKitchen(objArrangement.descriptionKitchen());
        vA.setDoItMySelf(objArrangement.doItMySelf());
        vA.setAccessoriesDescription(objArrangement.accessoriesDescription());
        vA.setVehicle(v);
        vehiclesArrangementRepository.save(vA);
        return v;
    }

    public VehiclesArrangement updateVehiclesArrangement(long idOwner, VehiclesArrangementDTO objArrangement) throws IOException {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        VehiclesArrangement vA = vehiclesArrangementRepository.findByIdVehicles(v.getId()).orElseThrow(
                () -> new NotFoundEx("there are no arrangement")
        );
        if (vA.getBeds() != objArrangement.bads()) {
            vA.setBeds(objArrangement.bads());
        }
        if (!vA.getDescriptionBeds().equals(objArrangement.descriptionBeds())) {
            vA.setDescriptionBeds(objArrangement.descriptionBeds());
        }
        if (vA.isBathroom() != objArrangement.bathroom()) {
            vA.setBathroom(objArrangement.bathroom());
        }
        if (vA.getDescriptionKitchen().equals(objArrangement.descriptionKitchen())) {
            vA.setDescriptionKitchen(objArrangement.descriptionKitchen());
        }
        if (vA.getDescriptionBathroom().equals(objArrangement.descriptionBathroom())) {
            vA.setDescriptionBathroom(objArrangement.descriptionBathroom());
        }
        if (vA.isWater() != objArrangement.water()) {
            vA.setWater(objArrangement.water());
        }

        if (vA.isHotWater() != objArrangement.hotWater()) {
            vA.setHotWater(objArrangement.hotWater());
        }
        if (vA.isKitchen() != objArrangement.kitchen()) {
            vA.setKitchen(objArrangement.kitchen());
        }
        if (vA.isFridge() != objArrangement.fridge()) {
            vA.setFridge(objArrangement.fridge());
        }

        if (vA.isGas() != objArrangement.gas()) {
            vA.setGas(objArrangement.gas());
        }

        if (vA.isDoItMySelf() != objArrangement.doItMySelf()) {
            vA.setDoItMySelf(objArrangement.doItMySelf());
        }
        if (!vA.getAccessoriesDescription().equals(objArrangement.accessoriesDescription())) {
            vA.setAccessoriesDescription(objArrangement.accessoriesDescription());
        }
        return vehiclesArrangementRepository.save(vA);

    }

}
