package dev.friox.database.controller;

import dev.friox.database.model.dto.request.UserCreateRequestDto;
import dev.friox.database.model.dto.request.UserUpdateRequestDto;
import dev.friox.database.model.dto.response.UserResponseDto;
import dev.friox.database.model.dto.response.UserUpdateResponseDto;
import dev.friox.database.persistence.jpa.entity.User;
import dev.friox.database.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "User", description = "사용자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // CREATE
    @Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "유저 생성 성공"),
            @ApiResponse(responseCode = "409", description = "유저 중복")
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequestDto dto) {
        User user = userService.createUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    // READ
    @Operation(summary = "유저 조회", description = "존재하는 유저를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "유저 조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUserByUserName(@PathVariable String username) {
        User user = userService.getUserByUserName(username);
        return ResponseEntity.ok(UserResponseDto.fromEntity(user));
    }

    // UPDATE
    @Operation(summary = "유저 정보 업데이트", description = "유저의 정보를 업데이트합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "유저 업데이트 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @PutMapping("/{username}")
    public ResponseEntity<UserUpdateResponseDto> updateUser(
            @PathVariable String username,
            @RequestBody UserUpdateRequestDto dto
    ) {
        User updatedUser = userService.updateUser(username, dto);
        UserUpdateResponseDto responseDto = UserUpdateResponseDto.fromEntity(updatedUser);
        return ResponseEntity.ok(responseDto);
    }

    // DELETE
    @Operation(summary = "유저 삭제", description = "유저를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "유저 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    // EXCEPTION HANDLER
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getReason());
    }

}
