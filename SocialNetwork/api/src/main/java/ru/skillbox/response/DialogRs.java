package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.skillbox.response.data.MessageDto;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DialogRs {

    private String error;
    private Long timestamp;
    private Integer total;
    private Integer offset;
    private List<MessageDto> data;
    private String errorDescription;
    private Integer perPage;
    private Integer currentUserId;

    public static DialogRs getMessageRs(Long id, Integer offset, Integer itemPerPage) {
        List<MessageDto> messageDtoList = new ArrayList<>();

        /*messageDtoList.add(MessageDto.builder()
                .id(1L)
                .authorId(id)
                .time(System.currentTimeMillis())
                .messageText("Миллион алых роз")
                .build()
        );

        messageDtoList.add(MessageDto.builder()
                .id(2L)
                .authorId(1L)
                .time(System.currentTimeMillis() + 1000L)
                .messageText("Из окна видишь ты")
                .build()
        );*/

        return DialogRs.builder()
                .timestamp(System.currentTimeMillis())
                .total(10)
                .offset(offset)
                .perPage(itemPerPage)
                .data(messageDtoList)
                .build();
    }

    public static DialogRs deleteDialog(Long id) {
        /*DialogRs dialogRs = new DialogRs();

        dialogRs.setError("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData deleteData = new DialogData();
        deleteData.setId(5L);
        dialogRs.setData(deleteData);

        dialogRs.setErrorDescription("Неверный код авторизации");
*/
        return null;
    }

    public static DialogRs getUnreadMsg() {
        /*DialogRs dialogRs = new DialogRs();

        dialogRs.setError("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData deleteData = new DialogData();
        deleteData.setCount(10L);
        dialogRs.setData(deleteData);

        dialogRs.setErrorDescription("Неверный код авторизации");
*/
        return null;
    }

    public static DialogRs createDialog() {
        /*DialogRs dialogRs = new DialogRs();

        dialogRs.setError("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData createDialogData = new DialogData();
        createDialogData.setId(1L);
        dialogRs.setData(createDialogData);

        dialogRs.setErrorDescription("Неверный код авторизации");
*/
        return null;
    }

    public static DialogRs markAsRead() {
/*

        DialogRs dialogRs = new DialogRs();

        dialogRs.setError("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData markAsReadData = new DialogData();
        markAsReadData.setMessage("Ok");
        dialogRs.setData(markAsReadData);

        dialogRs.setErrorDescription("Неверный код авторизации");
*/

        return null;
    }
}

