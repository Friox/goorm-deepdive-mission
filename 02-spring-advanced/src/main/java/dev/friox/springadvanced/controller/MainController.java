package dev.friox.springadvanced.controller;

import dev.friox.springadvanced.dto.UserSignUpDTO;
import dev.friox.springadvanced.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 애플리케이션의 모든 라우팅을 담당하는 컨트롤러.
 */
@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 메인 페이지 라우팅
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String isRegist) {
        // URL 매개변수로 받은 isRegist 변수의 존재에 따라 모델 객체에 값을 설정한다.
        // 해당 속성은 회원가입 성공 여부에 따라 메인화면에 알림창을 띄울지 결정하는 논리값이다.
        if (isRegist != null) model.addAttribute("isRegist", true);
        return "index";
    }

    /**
     * 회원가입 페이지 라우팅
     */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /**
     * 로그인 페이지 라우팅
     */
    @GetMapping("/signin")
    public String signin(HttpSession session, Model model) {
        // 만약 현재 세션에 로그인 관련하여 메시지가 존재할경우,
        Object attr = session.getAttribute("signinMessage");
        if (attr != null) {
            // 모델에 메시지를 옮기고 세션에 존재하는 메시지를 삭제한다.
            model.addAttribute("signinMessage", attr);
            session.removeAttribute("signinMessage");
        }
        return "signin";
    }

    /**
     * 게시물 작성 페이지 라우팅
     */
    @GetMapping("/post_write")
    public String postWrite(Model model) {
        return "post_write";
    }

    /**
     * 회원가입 페이지 POST 라우팅
     */
    @PostMapping("/signup")
    public String signUpUser(@Valid @ModelAttribute UserSignUpDTO dto, BindingResult result, RedirectAttributes attr) {
        /*
        * 시행착오 1
        * 현상 : @Valid 어노테이션에 의한 예외 발생이 안되는것처럼 보임
        * 원인 : 이후 따라오는 BindingResult가 예외를 처리하고 있었음
        * 해결 : 처리된 예외를 간접적으로 사용
        * DTO에 대해 Validation을 적용한 경우, 'MethodArgumentNotValidException' 예외가 발생한다.
        * 예외가 처리되지 않아 에러가 발생할 줄 알았으나, 어딘가에서 처리되어 넘어오지 않았다.
        * 이후에 따라오는 BindingResult 가 해당 예외를 Catch 하여 처리하고 있었다.
        * BindingResult 객체의 getFieldErrors() 메서드를 사용하면 필드에 관련한 오류를 가져올 수 있다.
        * 해당 오류들을 순회하면서 검증오류가 발생한 필드이름, 에러메시지를 Model 객체에 추가하여
        * 사용자에게 피드백하는데 사용한다.
        * */
        if (result.hasErrors()) { // 만약 오류가 있을경우
            List<FieldError> errors = result.getFieldErrors(); // 필드에 대한 모든 오류를 가져오고,
            errors.forEach(fieldError -> { // 오류 리스트를 순회하면서 일회성 속성에 저장한다.
                attr.addFlashAttribute("valid" + fieldError.getField(), fieldError.getDefaultMessage());
            });
            return "redirect:/signup"; // 이후 코드를 실행하지 않고 바로 리다이렉션.
        }
        try { // 오류가 없을 경우
            userService.signUpUser(dto); // UserService에 DTO를 전달하며 회원가입 진행
            return "redirect:/?isRegist"; // 회원가입 성공여부를 URL 매개변수로 담아 메인화면으로 리다이렉트
        } catch (Exception e) { // 오류가 발생할경우
            // 오류에 관한 정보를 속성에 저장하여 동일페이지로 리다이렉트
            attr.addFlashAttribute("validpassword", "비밀번호가 일치하지 않습니다");
            return "redirect:/signup";
        }
    }

}
