package ru.skillbox;

import ru.skillbox.response.CommentResponse;
import ru.skillbox.response.FeedsResponseOK;
import ru.skillbox.response.PostCommentDto;
import ru.skillbox.response.PostDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AdditionalFunctions {
    public static CommentResponse correctCommentContent(CommentResponse response, String timeString){
        List<PostCommentDto> postCommentDtoList = response.getContent();
        postCommentDtoList.forEach(p -> {
            p.setTime(timeString);
        });
        response.setContent(postCommentDtoList);
        return response;
    }

    public static FeedsResponseOK correctContent(FeedsResponseOK response, String timeString){
        List<PostDto> postDtoList = response.getContent();
        postDtoList.forEach(p -> {
            p.setTime(timeString);
            p.setPublishDate("2022-11-23T14:00:02.169Z");
        });
        response.setContent(postDtoList);

        return response;
    }

    public static String getTimeString() {
        long time = (new Date()).getTime();
        Timestamp timestamp = new Timestamp(time);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        String timeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        return timeString;
    }


    public static String generateExpectedCommentString(long personId, long postId, long postCommentId,
                                                       String timeString) {
        return "{" +
                "  \"totalElements\": 1," +
                "  \"totalPages\": 1," +
                "  \"number\": 0," +
                "  \"size\": 5," +
                "  \"content\": [" +
                "    {" +
                "      \"id\": " + postCommentId + "," +
                "      \"commentType\": \"POST\"," +
                "      \"time\": \"" + timeString + "\"," +
                "      \"timeChanged\": null," +
                "      \"authorId\": " + personId + "," +
                "      \"parentId\": 0," +
                "      \"commentText\": \"Text of comment\"," +
                "      \"postId\": " + postId + "," +
                "      \"isBlocked\": false," +
                "      \"isDelete\": false," +
                "      \"likeAmount\": 0," +
                "      \"myLike\": false," +
                "      \"commentsCount\": 1," +
                "      \"imagePath\": null" +
                "    }" +
                "  ]," +
                "  \"sort\": {" +
                "    \"empty\": false," +
                "    \"unsorted\": false," +
                "    \"sorted\": true" +
                "  }," +
                "  \"first\": true," +
                "  \"last\": true," +
                "  \"numberOfElements\": 1," +
                "  \"pageable\": {" +
                "    \"offset\": 0," +
                "    \"sort\": {" +
                "      \"empty\": false," +
                "      \"unsorted\": false," +
                "      \"sorted\": true" +
                "    }," +
                "    \"pageSize\": 5," +
                "    \"pageNumber\": 0," +
                "    \"paged\": true," +
                "    \"unpaged\": false" +
                "  }," +
                "  \"empty\": false" +
                "}";
    }

    public static String generateExpectedResponseString(long personId, long postId, String timeString){
        return "{" +
                "\"totalElements\": 1, " +
                "\"totalPages\": 1, " +
                "\"number\": 0, " +
                "\"size\": 5, " +
                "\"content\": [" +
                "{" +
                "\"id\": " + postId + "," +
                "\"time\": \"" + timeString + "\"," +
                "\"timeChanged\": \"\"," +
                "\"authorId\": " + personId + "," +
                "\"title\": \"SomeTitle\"," +
                "\"type\": \"POSTED\"," +
                "\"postText\": \"SomeText\"," +
                "\"isBlocked\": true," +
                "\"isDelete\": true," +
                "\"commentsCount\": 2," +
                "\"tags\": []," +
                "\"likeAmount\": 0," +
                "\"myLike\": false," +
                "\"imagePath\": null," +
                "\"publishDate\": \"2022-11-23T14:00:02.169Z\"" +
                "}" +
                "]," +
                "  \"sort\": {" +
                "    \"empty\": false," +
                "    \"unsorted\": false," +
                "    \"sorted\": true" +
                "  }," +
                "  \"first\": true," +
                "  \"last\": true," +
                "  \"numberOfElements\": 1," +
                "  \"pageable\": {" +
                "    \"offset\": 0," +
                "    \"sort\": {" +
                "      \"empty\": false," +
                "      \"unsorted\": false," +
                "      \"sorted\": true" +
                "    }," +
                "    \"pageSize\": 5," +
                "    \"pageNumber\": 0," +
                "    \"paged\": true," +
                "    \"unpaged\": false" +
                "  }," +
                "  \"empty\": false" +
                "}";
    }


    public static String generateExpectedSubCommentString(Long subCommentId,String timeString,
                                                          Long personId,
                                                          Long postCommentId,
                                                          Long postId){
        return "{" +
                "  \"totalElements\": 1," +
                "  \"totalPages\": 1," +
                "  \"number\": 0," +
                "  \"size\": 5," +
                "  \"content\": [" +
                "    {" +
                "      \"id\": " + subCommentId + "," +
                "      \"commentType\": \"POST\"," +
                "      \"time\": \"" + timeString + "\"," +
                "      \"timeChanged\": null," +
                "      \"authorId\": " + personId + "," +
                "      \"parentId\": " + postCommentId + "," +
                "      \"commentText\": \"Text of subcomment\"," +
                "      \"postId\": " + postId + "," +
                "      \"isBlocked\": false," +
                "      \"isDelete\": false," +
                "      \"likeAmount\": 0," +
                "      \"myLike\": false," +
                "      \"commentsCount\": 0," +
                "      \"imagePath\": null" +
                "    }" +
                "  ]," +
                "  \"sort\": {" +
                "    \"empty\": false," +
                "    \"unsorted\": false," +
                "    \"sorted\": true" +
                "  }," +
                "  \"first\": true," +
                "  \"last\": true," +
                "  \"numberOfElements\": 1," +
                "  \"pageable\": {" +
                "    \"offset\": 0," +
                "    \"sort\": {" +
                "      \"empty\": false," +
                "      \"unsorted\": false," +
                "      \"sorted\": true" +
                "    }," +
                "    \"pageSize\": 5," +
                "    \"pageNumber\": 0," +
                "    \"paged\": true," +
                "    \"unpaged\": false" +
                "  }," +
                "  \"empty\": false" +
                "}";
    }
}
