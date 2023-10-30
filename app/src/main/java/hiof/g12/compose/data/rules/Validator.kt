package hiof.g12.compose.data.rules

object Validator {

    fun validateUsername(username: String): ValidationResult {
        return ValidationResult(
            (!username.isNullOrEmpty())
        )

    }
    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrEmpty())
        )

    }
    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty())
        )

    }
}

data class ValidationResult(

    val status : Boolean = false
)