package rs.ac.uns.ftn.siit.sw442019.graduate.util;

public class Constants {
    //PATHS
    public static final String PHOTOS_FILE_PATH = "src/main/resources/images/";
    public static final String TARGET_PHOTO_FILE_PATH = "./src/main/resources/images/";
    public static final String TEMPLATE_FILE_PATH = "./src/main/resources/emailTemplates/";
    public static final String FRONT_VERIFY_URL = "https://localhost:4200/map/auth/verify/";
    public static final String SUBJECT_VERIFY_USER = "Aktivirajte svoj profil.";
    public static final String EMAIL = "domacemap@gmail.com";
    //MESSAGES
    public static final String WRONG_PASSWORD =
            "Password/confirm password must contain at least 8 characters. " +
                    "At least one number and one special character.";
    public static final String WRONG_EMAIL = "Email is not in the right format.";
    public static final String EMPTY_EMAIL = "Email cannot be empty.";
    public static final String TOO_LONG_EMAIL = "Email length is too long. Email cannot contain more than 60 characters.";
    public static final String WRONG_NAME = "Name must contain only letters and cannot be too long.";
    public static final String WRONG_SURNAME = "Surname must contain only letters and cannot be too long.";
    public static final String WRONG_ROLE = "Role must be selected.";
    public static final String POSITIVE_VALUE = "Price must be greater than 0.";
    public static final String NOT_NULL_MESSAGE = "Field must not be null.";
    public static final String POSITIVE_OR_ZERO_MESSAGE = "Value must be positive or zero.";
    public static final String POSITIVE_MESSAGE = "Value must be positive.";
    public static final String WRONG_COL = "Quantity is required field.";
    public static final String REQUIRED_ID = "Id of household is required.";
    public static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "Passwords don't match. Try again.";
    public static final String WRONG_SECURITY_CODE = "Security code is number greater than 0.";
    public static final String CITY_ERROR_MESSAGE = "City must contain between 1 and 20 characters.";
    public static final String STREET_ERROR_MESSAGE = "Street must contain between 1 and 20 characters.";
    public static final String NUMBER_ERROR_MESSAGE = "Number mustn't be empty.";
    public static final String PHONENUMBER_ERROR_MESSAGE = "Phone number must contain between 8 and 12 digits.";
    public static final String UPDATE_ERROR_MESSAGE = "Picture cannot be uploaded due to server error.";
    public static final String PASSWORD_NOT_LONG_ENOUGH = "Password must be at least 12 characters long.";
    public static final String WRONG_VERIFY_HASH = "Verify hash must be added.";
    public static final String NULL_VALUE = "Attribute must not be empty.";
    //CONSTANTS
    public static final int MIN_SECURITY_NUM = 1000;
    public static final int MAX_SECURITY_NUM = 9999;
    public static final int SALT_LENGTH = 4;
    public static final int ZERO_FAILED_ATTEMPTS = 0;
    public static final int MAX_NUM_VERIFY_TRIES = 3;
    public static final int MIN_SQ_AREA = 10;
    public static final int MAX_SQ_AREA = 600;

    //REGEX
    public static final String LEGIT_PASSWORD_REG = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,100}$";
    public static final String LEGIT_NAME_REG = "^[A-Za-z]{1,20}$";
    public static final String LEGIT_RE_CITY_AND_STREET_REG = "[a-zA-Z ]{1,20}";
    public static final String POSITIVE_WHOLE_NUMBER_REG = "[1-9][0-9]*";
    public static final String PHONE_NUMBER_REG = "[0-9]{8,12}";

}