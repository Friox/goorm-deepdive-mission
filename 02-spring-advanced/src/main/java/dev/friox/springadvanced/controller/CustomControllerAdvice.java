package dev.friox.springadvanced.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 컨트롤러의 공통 관심사를 처리하는 ControllerAdvice.
 */
@ControllerAdvice
public class CustomControllerAdvice {

    /**
     * 라우팅 되지 않은 엔드포인트에 대해 처리하는 핸들러,
     * 404.html 파일을 연결한다.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notfoundHandler(NoHandlerFoundException exception, Model model) {
        return "404";
    }

    /**
     * 요청에 대해 인증여부를 판단하여 Model에 유저네임을 넣는 메서드.
     */
    @ModelAttribute
    public void authenticationAttribute(Model model) {
        // SecurityContextHolder로부터 인증객체를 가져온 후,
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 해당 객체가 null이 아니고 인증되어있으며, 익명인증이 아닌경우에
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();

            // UserDetails의 인스턴스일경우 해당 객체로부터 유저네임을 가져와 모델에 설정한다.
            if (principal instanceof UserDetails) {
                model.addAttribute("username", ((UserDetails) principal).getUsername());
            }
        }
    }

}
