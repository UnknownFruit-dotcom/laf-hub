package com.fruit.serverwebapp.mapper;

import com.fruit.serverwebapp.dto.request.ItemRequest;
import com.fruit.serverwebapp.dto.response.ItemResponse;
import com.fruit.serverwebapp.model.Item;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "foundBy", ignore = true)
    @Mapping(target = "organization", ignore = true)
    Item toEntity(ItemRequest request);

    ItemResponse toResponse(Item item);

    List<ItemResponse> toResponseList(List<Item> items);
}
