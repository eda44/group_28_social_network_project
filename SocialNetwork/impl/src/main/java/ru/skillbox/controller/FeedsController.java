package ru.skillbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.model.FeedsInterface;
import ru.skillbox.repository.PostRepository;
import ru.skillbox.service.AccountService;
import ru.skillbox.service.FeedsService;



@RestController
public class FeedsController implements FeedsInterface {

    private static Logger logger = LogManager.getLogger(FeedsController.class);

    private final FeedsService feedsService;

    private PostRepository postRepository;

    private AccountService accountService;

    public static Logger getLogger() {
        return logger;
    }

    @Autowired
    public FeedsController(FeedsService feedsService, PostRepository postRepository, AccountService accountService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
        this.feedsService = feedsService;
    }

    @Override
    @GetMapping("/api/v1/feeds")
    public ResponseEntity<Object> getFeedsSearch(int page, int size, String[] sort, int offset, int limit) throws JsonProcessingException {
       return feedsService.getObjectResponseEntity(page,size,sort,offset,limit);
    }



}
