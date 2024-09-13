package org.example.ex2tdd.controller;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.example.ex2tdd.model.Student;
import org.example.ex2tdd.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/registration")
    public String inscription(Model model){
        model.addAttribute("user", new User() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return "";
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getFullName() {
                return "";
            }

            @Override
            public void setFullName(String s) {

            }

            @Override
            public Iterator<Group> getGroups() {
                return null;
            }

            @Override
            public String getPassword() {
                return "";
            }

            @Override
            public void setPassword(String s) {

            }

            @Override
            public Iterator<Role> getRoles() {
                return null;
            }

            @Override
            public UserDatabase getUserDatabase() {
                return null;
            }

            @Override
            public String getUsername() {
                return "";
            }

            @Override
            public void setUsername(String s) {

            }

            @Override
            public void addGroup(Group group) {

            }

            @Override
            public void addRole(Role role) {

            }

            @Override
            public boolean isInGroup(Group group) {
                return false;
            }

            @Override
            public boolean isInRole(Role role) {
                return false;
            }

            @Override
            public void removeGroup(Group group) {

            }

            @Override
            public void removeGroups() {

            }

            @Override
            public void removeRole(Role role) {

            }

            @Override
            public void removeRoles() {

            }
        });
        return "registration-form";
    }

    @PostMapping("/registration")
    public String inscriptionForm(@ModelAttribute("user") User user){
        authService.register(user);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String connexion(Model model){
        return "connexion-form";
    }

    @PostMapping("/login")
    public String connexionForm(@ModelAttribute("username") String username, @ModelAttribute("password") String password){
        boolean connected = authService.login(username, password);
        if(connected){
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
