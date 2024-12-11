package com.makrosoft.movies.mapper;

import org.mapstruct.*;

import com.makrosoft.movies.dto.request.rental.RentalDtoCreateRequest;
import com.makrosoft.movies.dto.response.rental.RentalDtoCreateResponse;
import com.makrosoft.movies.dto.response.rental.RentalDtoFindResponse;
import com.makrosoft.movies.model.Rental;

/**
 * Class that defines the mapper for converting between Rental entity and Rental DTOs.
 */
@Mapper(componentModel = "spring", uses = ICopyMapper.class)
public interface IRentalMapper {

    /**
     * Converts a Rental entity to a RentalDtoCreateResponse.
     *
     * @param rental The Rental entity to convert.
     * @return The corresponding RentalDtoCreateResponse.
     */
    RentalDtoCreateResponse toDtoCreate(final Rental rental);

    /**
     * Converts a Rental entity to a RentalDtoFindResponse.
     *
     * @param rental The Rental entity to convert.
     * @return The corresponding RentalDtoFindResponse.
     */
    RentalDtoFindResponse toDtoFind(final Rental rental);

    /**
     * Converts a RentalDtoCreateRequest to a Rental entity, ignoring certain fields.
     *
     * @param rentalDtoCreateRequest The RentalDtoCreateRequest to convert.
     * @return The corresponding Rental entity.
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "amountCharged", ignore = true),
        @Mapping(target = "returnDate", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "createUser", ignore = true),
        @Mapping(target = "updateTime", ignore = true),
        @Mapping(target = "updateUser", ignore = true),
        @Mapping(target = "copy", ignore = true)
    })
    Rental toEntityCreate(final RentalDtoCreateRequest rentalDtoCreateRequest);
}
