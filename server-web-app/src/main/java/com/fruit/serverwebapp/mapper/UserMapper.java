package com.fruit.serverwebapp.mapper;

import com.fruit.serverwebapp.dto.request.UserCreationRequest;
import com.fruit.serverwebapp.dto.request.UserUpdateRequest;
import com.fruit.serverwebapp.dto.response.UserResponse;
import com.fruit.serverwebapp.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserCreationRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateRequest dto, @MappingTarget User user);
}
