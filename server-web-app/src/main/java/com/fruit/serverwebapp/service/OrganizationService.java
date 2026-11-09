package com.fruit.serverwebapp.service;

import com.fruit.serverwebapp.config.PasscodeGenerator;
import com.fruit.serverwebapp.dto.request.OrganizationRequest;
import com.fruit.serverwebapp.dto.response.OrganizationCreatedResponse;
import com.fruit.serverwebapp.dto.response.OrganizationResponse;
import com.fruit.serverwebapp.dto.response.ParticipationResponse;
import com.fruit.serverwebapp.exception.AlreadyJoinedException;
import com.fruit.serverwebapp.exception.ResourceNotFoundException;
import com.fruit.serverwebapp.mapper.OrganizationMapper;
import com.fruit.serverwebapp.model.Organization;
import com.fruit.serverwebapp.model.User;
import com.fruit.serverwebapp.model.UserOrganization;
import com.fruit.serverwebapp.repository.OrganizationRepository;
import com.fruit.serverwebapp.repository.UserOrganizationRepository;
import com.fruit.serverwebapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrganizationService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final UserOrganizationRepository userOrganizationRepository;
    private final OrganizationMapper organizationMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasscodeGenerator passcodeGenerator;

    public List<OrganizationResponse> getAllOrgs() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizationMapper.toResponseList(organizations);
    }

    public OrganizationResponse getOrgById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
        return organizationMapper.toResponse(organization);
    }

    @Transactional
    public OrganizationCreatedResponse createOrg(OrganizationRequest request) {
        Organization organization = organizationMapper.toEntity(request);

        User user = userRepository.findById(request.createdById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + request.createdById()));
        organization.setCreatedBy(user);

        String passcode = passcodeGenerator.generatePasscode();
        organization.setVerificationCodeHash(passwordEncoder.encode(passcode));

        organization.setCreatedAt(LocalDateTime.now());

        Organization saved = organizationRepository.save(organization);
        return organizationMapper.toCreatedResponse(saved, passcode);
    }

    @Transactional
    public ParticipationResponse joinOrg(Long orgId, Long userId, String passcode) {
        Organization organization = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + orgId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));

        if (!passwordEncoder.matches(passcode, organization.getVerificationCodeHash())) {
            throw new BadCredentialsException("Invalid passcode");
        }

        if (userOrganizationRepository.existsByUserAndOrganization(user, organization)) {
            throw new AlreadyJoinedException("User with id " + user.getId() + " has already joined the organization \"" + organization.getTitle() + "\"");
        }

        var participation = new UserOrganization();
        participation.setOrganization(organization);
        participation.setUser(user);
        participation.setJoinedAt(LocalDateTime.now());

        return organizationMapper.toParticipationResponse(userOrganizationRepository.save(participation));
    }

    @Transactional
    public void deleteOrg(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Organization not found with id: " + id);
        }
        organizationRepository.deleteById(id);
    }
}
