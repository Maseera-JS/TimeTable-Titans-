package com.hackathon.TrainerTimeTable.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hackathon.TrainerTimeTable.service.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenHelper jwtService; // This validates & extracts username from token

    @Autowired
    private UserService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Remove "Bearer "
            try {
                username = jwtService.getUsernameFromToken(token);
            } catch (Exception e) {
                System.out.println("Invalid token: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
//                System.out.println(">>> AUTH SET: " + userDetails.getAuthorities());
            } else {
                System.out.println("Token validation failed");
            }
        }

        // Always continue the chain to avoid blocking the request
        filterChain.doFilter(request, response);
    }

    
    
    
    
    
    
    
    
    
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException, java.io.IOException {
//
//    	System.out.println("Inside the do internal method");
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7); // remove "Bearer "
//            try {
//                username = jwtService.getUsernameFromToken(token);
//            } catch (Exception e) {
//                System.out.println("Invalid token: " + e.getMessage());
//            }
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtService.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                
//    
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//                
//               
//            }
//        }
//        System.out.println(">>>>>>>>>>>>"+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//        
//        filterChain.doFilter(request, response);
//    }




}
