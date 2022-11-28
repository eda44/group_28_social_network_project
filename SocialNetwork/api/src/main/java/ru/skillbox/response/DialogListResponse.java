package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.dto.*;
import ru.skillbox.dto.enums.MessagePermission;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DialogListResponse {

    @JsonProperty("error")
    private String errMsg;
    private Long timestamp;
    private Integer total;
    private Integer offset;

    @JsonProperty("data")
    private List<DialogDto> dialogList;

    @JsonProperty("error_description")
    private String errorDesc;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("current_user_id")
    private Integer currentUserId;


    public static DialogListResponse getDialogListResponse(Integer offset, Integer itemPerPage) {

        DialogListResponse dialogListResponse = new DialogListResponse();
        dialogListResponse.setErrMsg("Неверный запрос");
        dialogListResponse.setTimestamp(System.currentTimeMillis());
        dialogListResponse.setTotal(10);
        dialogListResponse.setOffset(offset);

        List<DialogDto> dialogs = new ArrayList<>();
        DialogDto dialog = new DialogDto();
        dialog.setId(1L);

        AccountByIdDto conversationPartner = new AccountByIdDto();
        conversationPartner.setId(1L);
        conversationPartner.setEmail("dsiegertsz0@fc2.com");
        conversationPartner.setPhone("+7 645 943 5082");
        conversationPartner.setPhoto("data:image/png;base64...");
        conversationPartner.setAbout("Maecenas tristique...");

        CityDto city = new CityDto();
        city.setId(0L);
        city.setTitle("string");
        city.setCountryId(0);

        CountryDto country = new CountryDto();
        country.setId(0);
        country.setTitle("string");
        CityDto[] cities = {city};
        //country.setCities(cities);

        conversationPartner.setCity(city);
        conversationPartner.setCountry(country);
        conversationPartner.setFirstName("Davida");
        conversationPartner.setLastName("Siegertsz");
        conversationPartner.setRegDate(1618070680000L);
        conversationPartner.setBirthDate(702565308000L);
        conversationPartner.setMessagePermission(MessagePermission.ALL);
        conversationPartner.setLastOnlineTime(1644234125000L);
        conversationPartner.setIsOnline(true);
        conversationPartner.setIsBlocked(false);

        dialog.setConversationPartner(conversationPartner);
        dialog.setUnreadCount(15L);

        MessageDto lastMessage = new MessageDto();
        lastMessage.setId(1L);
        lastMessage.setTime(1464612365L);
        lastMessage.setStatus("SENT");
        lastMessage.setAuthorId(5L);
        lastMessage.setRecipientId(8L);
        lastMessage.setMessageText("Купи хлеб");

        dialog.setLastMessage(lastMessage);
        dialogs.add(dialog);

        dialogListResponse.setDialogList(dialogs);

        dialogListResponse.setErrorDesc("Неверный код авторизации");
        dialogListResponse.setPerPage(itemPerPage);
        dialogListResponse.setCurrentUserId(55);

        return dialogListResponse;
    }
}

