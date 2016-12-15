/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.agend.security.enumeration;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Henrique
 */
public enum AgendRoles implements GrantedAuthority {

    ROLE_USER;

    @Override
    public String getAuthority() {
        return toString();
    }

    public static List<AgendRoles> valuesToList() {
        return Arrays.asList(AgendRoles.values());
    }
}
