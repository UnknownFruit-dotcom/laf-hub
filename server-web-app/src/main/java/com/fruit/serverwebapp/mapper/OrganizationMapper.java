package com.fruit.serverwebapp.mapper;

import com.fruit.serverwebapp.dto.request.OrganizationRequest;
import com.fruit.serverwebapp.dto.request.OrganizationUpdateRequest;
import com.fruit.serverwebapp.dto.response.OrganizationCreatedResponse;
import com.fruit.serverwebapp.dto.response.OrganizationResponse;
import com.fruit.serverwebapp.dto.response.ParticipationResponse;
import com.fruit.serverwebapp.model.Organization;
import com.fruit.serverwebapp.model.UserOrganization;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "verificationCodeHash", ignore = true)
    Organization toEntity(OrganizationRequest request);

    OrganizationResponse toResponse(Organization organization);

    @Mapping(target = "verificationCode", source = "passcode")
    OrganizationCreatedResponse toCreatedResponse (Organization organization, String passcode);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "organizationId", source = "organization.id")
    ParticipationResponse toParticipationResponse (UserOrganization participation);

    List<OrganizationResponse> toResponseList(List<Organization> organizations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrganizationFromDto(OrganizationUpdateRequest dto, @MappingTarget Organization entity);
}
