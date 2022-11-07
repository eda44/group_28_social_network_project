package ru.skillbox.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedsCommentsQueryDto {
    public long id;
    private long time;
    private long personId;
    private String mail;
    private String personPhone;
    private String personPhoto;
    private String personAbout;
    private long cityId;
    private String cityTitle;
    private int countryId;
    private String countryTitle;
    private String firstName;
    private String lastName;
    private long regDate;
    private long birthDate;
    private String messagePermission;
    private long lastOnlineTime;
    private boolean isOnline;
    private boolean isBlocked;
    private String postCommentText;
    private boolean postCommentIsBlocked;

    private Integer parentId;
    private Integer postId;

    private String postTitle;
    private String postType;

}
