package com.fruit.serverwebapp.service;

import com.fruit.serverwebapp.dto.request.ChangeItemStatusRequest;
import com.fruit.serverwebapp.dto.request.ItemRequest;
import com.fruit.serverwebapp.dto.response.ItemResponse;
import com.fruit.serverwebapp.exception.ResourceNotFoundException;
import com.fruit.serverwebapp.exception.SameStatusException;
import com.fruit.serverwebapp.mapper.ItemMapper;
import com.fruit.serverwebapp.model.Item;
import com.fruit.serverwebapp.model.ItemStatus;
import com.fruit.serverwebapp.model.Organization;
import com.fruit.serverwebapp.model.User;
import com.fruit.serverwebapp.repository.ItemRepository;
import com.fruit.serverwebapp.repository.OrganizationRepository;
import com.fruit.serverwebapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final ItemMapper itemMapper;

    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        return itemMapper.toResponse(item);
    }

    public List<ItemResponse> getItems(ItemStatus status, Long organizationId, Long foundById) {
        return itemMapper.toResponseList(
                itemRepository.findFiltered(status, organizationId, foundById)
        );
    }

    @Transactional
    public ItemResponse createItem(ItemRequest request) {
        Item item = itemMapper.toEntity(request);

        User user = userRepository.findById(request.foundById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + request.foundById()));
        item.setFoundBy(user);

        Organization organization = organizationRepository.findById(request.organizationId())
                        .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id:" + request.organizationId()));
        item.setOrganization(organization);

        item.setCreatedAt(LocalDateTime.now());
        Item saved = itemRepository.save(item);
        return itemMapper.toResponse(saved);
    }

    @Transactional
    public ItemResponse changeItemStatus(Long itemId, ChangeItemStatusRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));

        if (item.getStatus().equals(request.newStatus())) {
            throw new SameStatusException("Item with id " + itemId + " already has this status (" + request.newStatus() + ")");
        }

        item.setStatus(request.newStatus());
        return itemMapper.toResponse(itemRepository.save(item));
    }

    @Transactional
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
}
