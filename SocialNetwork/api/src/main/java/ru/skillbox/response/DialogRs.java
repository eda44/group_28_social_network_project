package ru.skillbox.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DialogRs {

    @JsonProperty("error")
    private String errMsg;
    private Long timestamp;
    private Integer total;
    private Integer offset;

    private DialogData data;

    @JsonProperty("error_description")
    private String errorDesc;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("current_user_id")
    private Integer currentUserId;

    public static DialogRs getMessageRs(Long id, Integer offset, Integer itemPerPage) {
        DialogRs dialogRs = new DialogRs();

        dialogRs.setErrMsg("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());
        dialogRs.setTotal(10);
        dialogRs.setOffset(offset);

        DialogData messages = new DialogData();

        messages.setId(1L);
        messages.setTime(1464612365L);
        messages.setAuthorId(id);
        messages.setMessageText("Сообщение");

        dialogRs.setData(messages);

        dialogRs.setErrorDesc("Неверный код авторизации");
        dialogRs.setPerPage(itemPerPage);

        return dialogRs;
    }

    public static DialogRs deleteDialog(Long id) {
        DialogRs dialogRs = new DialogRs();

        dialogRs.setErrMsg("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData deleteData = new DialogData();
        deleteData.setId(5L);
        dialogRs.setData(deleteData);

        dialogRs.setErrorDesc("Неверный код авторизации");

        return dialogRs;
    }

    public static DialogRs getUnreadMsg() {
        DialogRs dialogRs = new DialogRs();

        dialogRs.setErrMsg("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData deleteData = new DialogData();
        deleteData.setCount(10L);
        dialogRs.setData(deleteData);

        dialogRs.setErrorDesc("Неверный код авторизации");

        return dialogRs;
    }

    public static DialogRs createDialog() {
        DialogRs dialogRs = new DialogRs();

        dialogRs.setErrMsg("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData createDialogData = new DialogData();
        createDialogData.setId(1L);
        dialogRs.setData(createDialogData);

        dialogRs.setErrorDesc("Неверный код авторизации");

        return dialogRs;
    }

    public static DialogRs markAsRead() {

        DialogRs dialogRs = new DialogRs();

        dialogRs.setErrMsg("Неверный запрос");
        dialogRs.setTimestamp(System.currentTimeMillis());

        DialogData markAsReadData = new DialogData();
        markAsReadData.setMessage("Ok");
        dialogRs.setData(markAsReadData);

        dialogRs.setErrorDesc("Неверный код авторизации");

        return dialogRs;
    }
}

