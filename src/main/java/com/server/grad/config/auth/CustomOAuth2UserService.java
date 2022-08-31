package com.server.grad.config.auth;


import com.server.grad.config.auth.dto.OAuthAttributes;
import com.server.grad.config.auth.dto.SessionUser;
import com.server.grad.domain.User;
import com.server.grad.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스 ex)구글, 카카오, 네이버
        String regristrationId = userRequest.getClientRegistration().getRegistrationId();
        // 로그인 진행 시 key가 되는 필드 값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // 서비스 통해서 가져온 OAuth2User의 attribute 담는 클래스.
        OAuthAttributes attributes = OAuthAttributes.of(regristrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveUser(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private User saveUser(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .orElse(userRepository.save(attributes.toEntity()));

        return user;
    }
}
