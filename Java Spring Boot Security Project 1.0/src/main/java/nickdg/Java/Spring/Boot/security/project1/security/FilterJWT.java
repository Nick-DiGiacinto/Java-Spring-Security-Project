package nickdg.Java.Spring.Boot.security.project1.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nickdg.Java.Spring.Boot.security.project1.entities.User;
import nickdg.Java.Spring.Boot.security.project1.exceptions.ExceptionUnauthorized;
import nickdg.Java.Spring.Boot.security.project1.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FilterJWT extends OncePerRequestFilter {
    @Autowired
    private ToolsJWT tools;
    @Autowired
    private ServiceUser us;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw new ExceptionUnauthorized("Invalid authorization header.");
        else {
            String token = authHeader.replace("Bearer ", "");
            tools.verifyToken(token);
            String userId = tools.getIdFromToken(token);
            User current = us.findById(Long.parseLong(userId));
            Authentication a = new UsernamePasswordAuthenticationToken(current, null, current.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
