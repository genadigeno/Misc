package com.web.app.controller;

import com.web.app.response.AlbumRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2ClientService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @GetMapping("/albums")
    public String albumsGet(Model model) {

        String uri = "http://localhost:8082/albums";

        List<AlbumRest> entityBody = webClient.get()
                                            .uri(uri)
                                            .retrieve()
                                            .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>(){}).block();
        model.addAttribute("albums", entityBody);

        return "albums";
    }

    @GetMapping("/old/albums")
    public String albumsOld(Model model/*, @AuthenticationPrincipal OidcUser principal*/) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2Client = oAuth2ClientService.loadAuthorizedClient(
            oAuth2Token.getAuthorizedClientRegistrationId(),
            oAuth2Token.getName()
        );
        String jwtAccessToken = oAuth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);

        /*OidcIdToken idToken = principal.getIdToken();
        String token = idToken.getTokenValue();
        System.out.println("token = " + token);*/

        /*AlbumRest album = new AlbumRest();
        album.setAlbumTitle("tittl");
        model.addAttribute("albums", Collections.singletonList(album));*/

        String uri = "http://localhost:8082/albums";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+jwtAccessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(
            uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {}
        );

        List<AlbumRest> entityBody = responseEntity.getBody();
        model.addAttribute("albums", entityBody);

        return "albums-old";
    }
}
