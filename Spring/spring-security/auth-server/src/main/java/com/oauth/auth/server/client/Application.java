package com.oauth.auth.server.client;

import com.oauth.auth.server.util.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import java.util.*;

public class Application implements ClientDetails {
    private String clientId;
    private String clientSecret;
    private ClientType clientType;
    private Set<String> resourceIds = new HashSet<String>();
    private Set<String> scope = new HashSet<String>();
    private Set<String> registeredRedirectUri = new HashSet<String>();
    private Integer accessTokenValiditySeconds;
    private Map<String, Object> additionalInformation = new HashMap<String, Object>();

    public void setName(String name) {
        this.additionalInformation.put("name", name);
    }

    public void setClientType(ClientType clientType) {
        this.additionalInformation.put("client_type", clientType);
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }
    //---------------------------------------------------------------------------------------------------------//

    public void addRedirectUri(String redirectUri) {
        this.registeredRedirectUri.add(redirectUri);
    }

    public void addScope(String scope) {
        this.scope.add(scope);
    }

    public void addResourceId(String resourceId) {
        this.resourceIds.add(resourceId);
    }
    //---------------------------------------------------------------------------------------------------------//

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientType == ClientType.CONFIDENTIAL;
    }

    @Override
    public boolean isScoped() {
        return this.scope.size() > 0;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> grantTypes = new HashSet<String>();
        grantTypes.add(Constant.GRANT_TYPE.AUTHORIZATION_CODE);
        grantTypes.add(Constant.GRANT_TYPE.REFRESH_TOKEN);
        return grantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
