package com.haingue.tp1.CommunityBookstore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePageLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));

        mockMvc.perform(get("/"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Bienvenue dans la Bibliothèque Communautaire")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Livres disponibles")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Emprunter/Retourner")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Tableau de bord")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("H2-Console")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("API Documentation")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/ui/book/catalogue")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/ui/borrow")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/ui/administrator/dashboard")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/h2-console")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("/swagger-ui/index.html")));
    }

    /*
    @Test()
    public void testHomePageLinks() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(xpath("//a[href='/ui/book/catalogue']").exists())
                .andExpect(xpath("//a[href='/ui/borrow']").exists())
                .andExpect(xpath("//a[href='/ui/administrator/dashboard']").exists())
                .andExpect(xpath("//a[href='/h2-console']").exists())
                .andExpect(xpath("//a[href='/swagger-ui/index.html']").exists());
    }
     */
}