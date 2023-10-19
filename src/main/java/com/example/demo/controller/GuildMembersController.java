package com.example.demo.controller;

import com.example.demo.Model.GuildMember;
import com.example.demo.Service.GuildMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class GuildMembersController {

    private final GuildMemberService guildMembersService;
    private final RestTemplate restTemplate;

    @Autowired
    public GuildMembersController(GuildMemberService guildMembersService, RestTemplate restTemplate) {
        this.guildMembersService = guildMembersService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/members")
    public ResponseEntity<List<GuildMember>> getMembers() {
        return new ResponseEntity<>(guildMembersService.processLuaFile(), HttpStatus.OK);
    }
}





