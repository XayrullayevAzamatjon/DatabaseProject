package com.project.db.api;

import com.project.db.model.response.NewWordResponse;
import com.project.db.service.AdminService;
import com.project.db.utils.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/requested")
    public List<NewWordResponse> findAllRequestedWords(){
        return adminService.findAllRequestedWords();
    }
    @GetMapping("/search/{query}")
    public List<NewWordResponse> findAllSearchedWords(@PathVariable String query){
        return adminService.searchNewWords(query);
    }
    @GetMapping("/status/{status}")
    public List<NewWordResponse> findAllStatusWords(@PathVariable Status status){
        return adminService.findByStatus(status);
    }

    @GetMapping("/confirm/{newWordId}")
    public Status confirmNewWord(@PathVariable String newWordId){
        return adminService.confirmNewWord(newWordId);
    }

    @GetMapping("/denied/{newWordId}")
    public Status deniedNewWord(@PathVariable String newWordId){
        return adminService.deniedNewWOrd(newWordId);
    }
}
