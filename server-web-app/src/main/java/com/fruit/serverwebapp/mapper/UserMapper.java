package com.fruit.serverwebapp.mapper;

import com.fruit.serverwebapp.dto.request.UserCreationRequest;
import com.fruit.serverwebapp.dto.response.UserResponse;
import com.fruit.serverwebapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserCreationRequest request);

    UserResponse toResponse(User user);
}
