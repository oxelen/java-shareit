package ru.practicum.shareit.request;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.request.dto.CreateItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.service.ItemRequestService;
import ru.practicum.shareit.user.dto.UserRequestDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Transactional
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemRequestServiceImplTest {
    private final EntityManager em;
    private final ItemRequestService service;
    private final UserService userService;

    private Long userId;

    @BeforeEach
    public void setUp() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("testUserName");
        userRequestDto.setEmail("testUserEmail");

        userId = userService.create(userRequestDto).getId();
    }

    @Test
    public void createTest() {
        CreateItemRequestDto requestDto = new CreateItemRequestDto();
        requestDto.setDescription("testRequest");

        service.create(requestDto, userId);

        TypedQuery<ItemRequest> query = em.createQuery("select i" +
                        " from ItemRequest i" +
                        " where i.description = :description",
                ItemRequest.class);
        ItemRequest res = query.setParameter("description", requestDto.getDescription()).getSingleResult();

        assertThat(res.getId(), notNullValue());
        assertThat(res.getDescription(), equalTo(requestDto.getDescription()));
    }

    @Test
    public void getItemRequestByUserTest() {
        ItemRequest request = createDefaultItemRequest();
        List<ItemRequestDto> res = service.getItemRequestsByUser(userId);

        assertThat(res.size(), equalTo(1));

        ItemRequestDto requestRes = res.getFirst();
        assertThat(requestRes.getId(), equalTo(request.getId()));
    }

    @Test
    public void getAllTest() {
        createDefaultItemRequest();
        List<ItemRequestDto> res = service.getAll();

        assertThat(res.size(), equalTo(1));
    }

    @Test
    public void getItemRequestByIdTEst() {
        ItemRequest req = createDefaultItemRequest();
        Long reqId = req.getId();

        ItemRequestDto res = service.getItemRequestById(reqId);

        assertThat(res.getId(), equalTo(reqId));
        assertThat(res.getDescription(), equalTo(req.getDescription()));
    }

    private ItemRequest createDefaultItemRequest() {
        CreateItemRequestDto requestDto = new CreateItemRequestDto();
        requestDto.setDescription("testRequest");

        service.create(requestDto, userId);

        TypedQuery<ItemRequest> query = em.createQuery("select i" +
                        " from ItemRequest i" +
                        " where i.description = :description",
                ItemRequest.class);
        return query.setParameter("description", requestDto.getDescription()).getSingleResult();
    }
}
