package simonedangelo.mondovan.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Type;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesServicesStatusDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultsController {
    @Autowired
    ResultsService resultsService;

    @GetMapping("/date")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDate(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "16") int size,
                                                                       @RequestParam(defaultValue = "id") String sort,
                                                                       @RequestParam() LocalDate start,
                                                                       @RequestParam() LocalDate end) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateOnly(start, end);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_price")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndMoney(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "16") int size,
                                                                               @RequestParam(defaultValue = "id") String sort,
                                                                               @RequestParam() LocalDate start,
                                                                               @RequestParam() LocalDate end,
                                                                               @RequestParam() int price) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndMoney(start, end, price);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_price_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByPriceAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                           @RequestParam(defaultValue = "16") int size,
                                                                                           @RequestParam(defaultValue = "id") String sort,
                                                                                           @RequestParam() LocalDate start,
                                                                                           @RequestParam() LocalDate end,
                                                                                           @RequestParam() int price,
                                                                                           @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndMoneyAndSupply(start, end, price, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_price_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByPriceAndType(@RequestParam(defaultValue = "0") int page,
                                                                                         @RequestParam(defaultValue = "16") int size,
                                                                                         @RequestParam(defaultValue = "id") String sort,
                                                                                         @RequestParam() LocalDate start,
                                                                                         @RequestParam() LocalDate end,
                                                                                         @RequestParam() int price,
                                                                                         @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndMoneyAndType(start, end, price, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_price_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByPriceAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                  @RequestParam(defaultValue = "16") int size,
                                                                                                  @RequestParam(defaultValue = "id") String sort,
                                                                                                  @RequestParam() LocalDate start,
                                                                                                  @RequestParam() LocalDate end,
                                                                                                  @RequestParam() int price,
                                                                                                  @RequestParam() Supply supply,
                                                                                                  @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndMoneyAndSupplyAndType(start, end, price, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }


    @GetMapping("/date_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "16") int size,
                                                                                @RequestParam(defaultValue = "id") String sort,
                                                                                @RequestParam() LocalDate start,
                                                                                @RequestParam() LocalDate end,
                                                                                @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndSupply(start, end, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndType(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "16") int size,
                                                                              @RequestParam(defaultValue = "id") String sort,
                                                                              @RequestParam() LocalDate start,
                                                                              @RequestParam() LocalDate end,
                                                                              @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndType(start, end, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                       @RequestParam(defaultValue = "16") int size,
                                                                                       @RequestParam(defaultValue = "id") String sort,
                                                                                       @RequestParam() LocalDate start,
                                                                                       @RequestParam() LocalDate end,
                                                                                       @RequestParam() Supply supply,
                                                                                       @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndSupplyAndType(start, end, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndBeds(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "16") int size,
                                                                              @RequestParam(defaultValue = "id") String sort,
                                                                              @RequestParam() LocalDate start,
                                                                              @RequestParam() LocalDate end,
                                                                              @RequestParam() int beds) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndBeds(start, end, beds);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "16") int size,
                                                                                          @RequestParam(defaultValue = "id") String sort,
                                                                                          @RequestParam() LocalDate start,
                                                                                          @RequestParam() LocalDate end,
                                                                                          @RequestParam() int beds,
                                                                                          @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndBedsAndSupply(start, end, beds, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndType(@RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "16") int size,
                                                                                        @RequestParam(defaultValue = "id") String sort,
                                                                                        @RequestParam() LocalDate start,
                                                                                        @RequestParam() LocalDate end,
                                                                                        @RequestParam() int beds,
                                                                                        @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndBedsAndType(start, end, beds, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                 @RequestParam(defaultValue = "16") int size,
                                                                                                 @RequestParam(defaultValue = "id") String sort,
                                                                                                 @RequestParam() LocalDate start,
                                                                                                 @RequestParam() LocalDate end,
                                                                                                 @RequestParam() int beds,
                                                                                                 @RequestParam() Supply supply,
                                                                                                 @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndBedsAndSupplyAndType(start, end, beds, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }


    @GetMapping("/date_province")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndProvince(@RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "16") int size,
                                                                                  @RequestParam(defaultValue = "id") String sort,
                                                                                  @RequestParam() LocalDate start,
                                                                                  @RequestParam() LocalDate end,
                                                                                  @RequestParam() String province) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndProvince(start, end, province);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "16") int size,
                                                                                          @RequestParam(defaultValue = "id") String sort,
                                                                                          @RequestParam() LocalDate start,
                                                                                          @RequestParam() LocalDate end,
                                                                                          @RequestParam() String prov,
                                                                                          @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndProvAndSupply(start, end, prov, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvAndType(@RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "16") int size,
                                                                                        @RequestParam(defaultValue = "id") String sort,
                                                                                        @RequestParam() LocalDate start,
                                                                                        @RequestParam() LocalDate end,
                                                                                        @RequestParam() String prov,
                                                                                        @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndProvAndType(start, end, prov, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                 @RequestParam(defaultValue = "16") int size,
                                                                                                 @RequestParam(defaultValue = "id") String sort,
                                                                                                 @RequestParam() LocalDate start,
                                                                                                 @RequestParam() LocalDate end,
                                                                                                 @RequestParam() String prov,
                                                                                                 @RequestParam() Supply supply,
                                                                                                 @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndProvAndSupplyAndType(start, end, prov, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }


    @GetMapping("/date_prov_beds")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndBeds(@RequestParam(defaultValue = "0") int page,
                                                                                            @RequestParam(defaultValue = "16") int size,
                                                                                            @RequestParam(defaultValue = "id") String sort,
                                                                                            @RequestParam() LocalDate start,
                                                                                            @RequestParam() LocalDate end,
                                                                                            @RequestParam() int beds,
                                                                                            @RequestParam() String province) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndBeds(start, end, beds, province);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_beds_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndBedsAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                                     @RequestParam(defaultValue = "16") int size,
                                                                                                     @RequestParam(defaultValue = "id") String sort,
                                                                                                     @RequestParam() LocalDate start,
                                                                                                     @RequestParam() LocalDate end,
                                                                                                     @RequestParam() int beds,
                                                                                                     @RequestParam() String province,
                                                                                                     @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndBedsAndSupply(start, end, beds, province, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_beds_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndBedsAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                   @RequestParam(defaultValue = "16") int size,
                                                                                                   @RequestParam(defaultValue = "id") String sort,
                                                                                                   @RequestParam() LocalDate start,
                                                                                                   @RequestParam() LocalDate end,
                                                                                                   @RequestParam() int beds,
                                                                                                   @RequestParam() String province,
                                                                                                   @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndBedsAndType(start, end, beds, province, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_beds_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndBedsAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                            @RequestParam(defaultValue = "16") int size,
                                                                                                            @RequestParam(defaultValue = "id") String sort,
                                                                                                            @RequestParam() LocalDate start,
                                                                                                            @RequestParam() LocalDate end,
                                                                                                            @RequestParam() int beds,
                                                                                                            @RequestParam() String province,
                                                                                                            @RequestParam() Supply supply,
                                                                                                            @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndBedsAndSupplyAndType(start, end, beds, province, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_province_price")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndProvinceAndPrice(@RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "16") int size,
                                                                                          @RequestParam(defaultValue = "id") String sort,
                                                                                          @RequestParam() LocalDate start,
                                                                                          @RequestParam() LocalDate end,
                                                                                          @RequestParam() String province,
                                                                                          @RequestParam() int price) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndProvinceAndPrice(start, end, province, price);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_price_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndPriceAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                                      @RequestParam(defaultValue = "16") int size,
                                                                                                      @RequestParam(defaultValue = "id") String sort,
                                                                                                      @RequestParam() LocalDate start,
                                                                                                      @RequestParam() LocalDate end,
                                                                                                      @RequestParam() int price,
                                                                                                      @RequestParam() String province,
                                                                                                      @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndPriceAndSupply(start, end, province, price, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_price_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndPriceAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                    @RequestParam(defaultValue = "16") int size,
                                                                                                    @RequestParam(defaultValue = "id") String sort,
                                                                                                    @RequestParam() LocalDate start,
                                                                                                    @RequestParam() LocalDate end,
                                                                                                    @RequestParam() int price,
                                                                                                    @RequestParam() String province,
                                                                                                    @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndPriceAndType(start, end, province, price, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_price_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndPriceAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                             @RequestParam(defaultValue = "16") int size,
                                                                                                             @RequestParam(defaultValue = "id") String sort,
                                                                                                             @RequestParam() LocalDate start,
                                                                                                             @RequestParam() LocalDate end,
                                                                                                             @RequestParam() int price,
                                                                                                             @RequestParam() String province,
                                                                                                             @RequestParam() Supply supply,
                                                                                                             @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndPriceAndSupplyAndType(start, end, province, price, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }


    @GetMapping("/date_beds_price")
    public Page<VehiclesServicesStatusDTO> vehicleAvailableByRangeDateAndBedsAndPrice(@RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "16") int size,
                                                                                      @RequestParam(defaultValue = "id") String sort,
                                                                                      @RequestParam() LocalDate start,
                                                                                      @RequestParam() LocalDate end,
                                                                                      @RequestParam() int beds,
                                                                                      @RequestParam() int price) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateAndBedsAndPrice(start, end, beds, price);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_price_supply")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndPriceAndSupply(@RequestParam(defaultValue = "0") int page,
                                                                                                  @RequestParam(defaultValue = "16") int size,
                                                                                                  @RequestParam(defaultValue = "id") String sort,
                                                                                                  @RequestParam() LocalDate start,
                                                                                                  @RequestParam() LocalDate end,
                                                                                                  @RequestParam() int price,
                                                                                                  @RequestParam() int beds,
                                                                                                  @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByBedsAndPriceAndSupply(start, end, beds, price, supply);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_price_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndPriceAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                @RequestParam(defaultValue = "16") int size,
                                                                                                @RequestParam(defaultValue = "id") String sort,
                                                                                                @RequestParam() LocalDate start,
                                                                                                @RequestParam() LocalDate end,
                                                                                                @RequestParam() int price,
                                                                                                @RequestParam() int beds,
                                                                                                @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByBedsAndPriceAndType(start, end, beds, price, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_price_supply_type")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndType(@RequestParam(defaultValue = "0") int page,
                                                                                                         @RequestParam(defaultValue = "16") int size,
                                                                                                         @RequestParam(defaultValue = "id") String sort,
                                                                                                         @RequestParam() LocalDate start,
                                                                                                         @RequestParam() LocalDate end,
                                                                                                         @RequestParam() int price,
                                                                                                         @RequestParam() int beds,
                                                                                                         @RequestParam() Supply supply,
                                                                                                         @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndType(start, end, beds, price, supply, type);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_prov_beds_price")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByProvinceAndBedsAndPrice(@RequestParam(defaultValue = "0") int page,
                                                                                                    @RequestParam(defaultValue = "16") int size,
                                                                                                    @RequestParam(defaultValue = "id") String sort,
                                                                                                    @RequestParam() LocalDate start,
                                                                                                    @RequestParam() LocalDate end,
                                                                                                    @RequestParam() int beds,
                                                                                                    @RequestParam() String province,
                                                                                                    @RequestParam() int price) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByProvinceAndBedsAndPrice(start, end, beds, province, price);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_price_supply_prov")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndProv(@RequestParam(defaultValue = "0") int page,
                                                                                                         @RequestParam(defaultValue = "16") int size,
                                                                                                         @RequestParam(defaultValue = "id") String sort,
                                                                                                         @RequestParam() LocalDate start,
                                                                                                         @RequestParam() LocalDate end,
                                                                                                         @RequestParam() int price,
                                                                                                         @RequestParam() int beds, @RequestParam() String prov,
                                                                                                         @RequestParam() Supply supply) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByBedsAndPriceAndSupply(start, end, beds, price, supply, prov);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_price_type_prov")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndPriceAndTypeAndProv(@RequestParam(defaultValue = "0") int page,
                                                                                                       @RequestParam(defaultValue = "16") int size,
                                                                                                       @RequestParam(defaultValue = "id") String sort,
                                                                                                       @RequestParam() LocalDate start,
                                                                                                       @RequestParam() LocalDate end,
                                                                                                       @RequestParam() int price,
                                                                                                       @RequestParam() int beds, @RequestParam() String prov,
                                                                                                       @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByBedsAndPriceAndType(start, end, beds, price, type, prov);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/date_beds_price_supply_type_prov")
    public Page<VehiclesServicesStatusDTO> vehicleAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndTypeAndProv(@RequestParam(defaultValue = "0") int page,
                                                                                                                @RequestParam(defaultValue = "16") int size,
                                                                                                                @RequestParam(defaultValue = "id") String sort,
                                                                                                                @RequestParam() LocalDate start,
                                                                                                                @RequestParam() LocalDate end,
                                                                                                                @RequestParam() int price,
                                                                                                                @RequestParam() int beds,
                                                                                                                @RequestParam() String prov,
                                                                                                                @RequestParam() Supply supply,
                                                                                                                @RequestParam() Type type) throws Exception {
        List<VehiclesServicesStatusDTO> vehiclesList = resultsService.getByAvailabilityAndRangeDateByBedsAndPriceAndSupplyAndType(start, end, beds, price, supply, type, prov);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

}
