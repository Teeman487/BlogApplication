package com.adebusoye.blogapplication.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
//Whenever a user logged-in our blog app, user will create the blog post[NewPost], then we need to store that userID in a posts table[createdBy]
//i.e as an ADMIN, After logging in with[User], i can create many posts for 1 User
// First we write the code to get the logged-in (user-ID) from the spring security
public class SecurityUtils {  // Refactor Create Post Feature For Logged in User
    public static User getCurrentUser(){ // First we write the code to get the logged-in user-ID from the spring security
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof User){ // convert principal object into the user object from spring security
            return (User) principle;
        }                   // Now, we got the current logged-in user from SecurityContextHolder class
        return null;
    }


    /*109 Refactor List Posts and Comments Feature for Admin*/

    public static String getRole(){ //
        User user = getCurrentUser(); /// it has list of roles// we got the current logged-in user from SecurityContextHolder class
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        for(GrantedAuthority authority: authorities){
            return authority.getAuthority(); // return current logged in user role for ADMIN
        }
        return null;
    }
}
