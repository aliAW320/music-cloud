package com.cloud.music_cloud.Tools;

import com.cloud.music_cloud.DAOS.UserDAO;
import com.cloud.music_cloud.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class UserExtractor {

    private final UserDAO userDAO;

    @Autowired
    public UserExtractor(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Users extract(Principal principal) {
        String username = principal.getName();  // Username from JWT token
        return userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}


