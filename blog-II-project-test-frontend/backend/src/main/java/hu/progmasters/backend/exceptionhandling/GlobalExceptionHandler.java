package hu.progmasters.backend.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        validationErrors.forEach(validationError -> {
            log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountWithUsernameAlreadyExistsException.class)
    public ResponseEntity<List<ValidationError>> handleAccountWithUsernameAlreadyExistsException(AccountWithUsernameAlreadyExistsException exception) {
        ValidationError validationError = new ValidationError("username",
                "Már létezik ilyen felhasználónévvel felhasználó: " + exception.getUsername());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountWithEmailAlreadyExistsException.class)
    public ResponseEntity<List<ValidationError>> handleAccountWithEmailAlreadyExistsException(AccountWithEmailAlreadyExistsException exception) {
        ValidationError validationError = new ValidationError("email",
                "Már létezik ilyen email-el felhasználó: " + exception.getEmail());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountWithDisplayNameAlreadyExistsException.class)
    public ResponseEntity<List<ValidationError>> handleAccountWithDisplayNameAlreadyExistsException(AccountWithDisplayNameAlreadyExistsException exception) {
        ValidationError validationError = new ValidationError("displayName",
                "Már létezik ilyen megjelenített névvel felhasználó: " + exception.getDisplayName());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountEmailFormatInvalidException.class)
    public ResponseEntity<List<ValidationError>> handleAccountEmailFormatInvalidException(AccountEmailFormatInvalidException exception) {
        ValidationError validationError = new ValidationError("email",
                "Az email formátuma nem megfelelő: " + exception.getEmail());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handleAccountNotFoundByIdException(AccountNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("id",
                "Nem található ilyen id-val felhasználó: " + exception.getId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryWithNameAlreadyExistsException.class)
    public ResponseEntity<List<ValidationError>> handleCategoryWithNameAlreadyExistsException(CategoryWithNameAlreadyExistsException exception) {
        ValidationError validationError = new ValidationError("name",
                "Már létezik ilyen névvel kategória: " + exception.getName());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handleCategoryNotFoundByIdException(CategoryNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("id",
                "Nem található ilyen id-val kategória: " + exception.getId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handlePostNotFoundByIdException(PostNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("id",
                "Nem található ilyen id-val poszt: " + exception.getId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NewPasswordEqualsOldPasswordException.class)
    public ResponseEntity<List<ValidationError>> handleNewPasswordEqualsOldPasswordException(NewPasswordEqualsOldPasswordException exception) {
        ValidationError validationError = new ValidationError("newPassword",
                "Az új jelszó megegyezik a régi jelszóval: " + exception.getNewPassword());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NewPasswordAndNewPasswordAgainNotEqualException.class)
    public ResponseEntity<List<ValidationError>> handleNewPasswordAndNewPasswordAgainNotEqualException(NewPasswordAndNewPasswordAgainNotEqualException exception) {
        ValidationError validationError = new ValidationError("newPassword - newPasswordAgain",
                "Az új jelszó és az új jelszó újra nem egyezik: " + exception.getNewPassword() + " - " + exception.getNewPasswordAgain());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OldPasswordNotCorrectException.class)
    public ResponseEntity<List<ValidationError>> handleOldPasswordNotCorrectException(OldPasswordNotCorrectException exception) {
        ValidationError validationError = new ValidationError("oldPassword",
                "A régi jelszó helytelen: " + exception.getOldPassword());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordFormatInvalidException.class)
    public ResponseEntity<List<ValidationError>> handlePasswordFormatInvalidException(PasswordFormatInvalidException exception) {
        ValidationError validationError = new ValidationError("password - " + exception.getPassword(),
                "A jelszó formátuma nem megfelelő. A következő szabályoknak kell megfelelnie: \n" +
                        "- Legalább egy számnak kell lennie benne\n" +
                        "- Legalább egy kisbetűnek kell lennie benne\n" +
                        "- Legalább egy nagybetűnek kell lennie benne\n" +
                        "- Nem lehet benne üres karakter\n" +
                        "- Legalább 5 karakternek kell lennie");
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
