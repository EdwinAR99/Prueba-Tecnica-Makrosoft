package com.makrosoft.movies.mapper;

import org.mapstruct.*;

import com.makrosoft.movies.dto.request.rental.RentalDtoCreateRequest;
import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.dto.response.rental.RentalDtoFindResponse;
import com.makrosoft.movies.model.Rental;

/**
 * Class that defines the mapper for converting between Rental entity and Rental DTOs.
 */
@Mapper(componentModel = "spring")
public interface IRentalMapper {

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "rentalDate", source = "rentalDate"),
        @Mapping(target = "dueDate", source = "dueDate"),
        @Mapping(target = "amountCharged", source = "amountCharged"),
        @Mapping(target = "customer", source = "customer"),
        //@Mapping(target = "copyId", source = "copy.id"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime")
    })
    RentalDtoCreateResponse toDtoCreate(final Rental rental);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "rentalDate", source = "rentalDate"),
        @Mapping(target = "dueDate", source = "dueDate"),
        @Mapping(target = "returnDate", source = "returnDate"),
        @Mapping(target = "amountCharged", source = "amountCharged"),
        @Mapping(target = "customer", source = "customer"),
        //@Mapping(target = "copyId", source = "copy.id"),
        @Mapping(target = "createUser", source = "createUser"),
        @Mapping(target = "createTime", source = "createTime"),
        @Mapping(target = "updateUser", source = "updateUser"),
        @Mapping(target = "updateTime", source = "updateTime")
    })
    RentalDtoFindResponse toDtoFind(final Rental rental);

    @Mappings({
        @Mapping(target = "rentalDate", source = "rentalDate"),
        @Mapping(target = "dueDate", source = "dueDate"),
        @Mapping(target = "customer", source = "customer"),
    })
    Rental toEntityCreate(final RentalDtoCreateRequest rentalDtoCreateRequest);
}
