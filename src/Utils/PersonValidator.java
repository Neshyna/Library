package Utils;

public class PersonValidator {

    private String email;
    private String password;

    public PersonValidator(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        if (isEmailValid(email)){
            this.email = email;
        }
    }

    public void setPassword(String password) {
        if (isPasswordValid(password)) {
            this.password = password;
        }
    }

    public static boolean isEmailValid(String email) {
    /*
    Task 1
    Закончить реализацию метода проверки email на валидность в классе Person
    Требования к email:
    test@email.com.net
    1. Должна присутствовать @ и только одна
    2. Точка после собаки
    3. После последней точки есть 2 или более символов
    4. Алфавит, цифры, '-', '_', '.', '@'
    5. До собаки должен быть хотя бы 1 символ
    6. Первый символ - должна быть буква
     */

        //6
        char firstSymbol = email.charAt(0);
        if (!Character.isLetter(firstSymbol)) return false;

        //1
        int indexAt = email.indexOf("@");
        if (indexAt == -1 || indexAt != email.lastIndexOf("@")) return false;

        //5
        if (indexAt == 0) return false;

        //2
        int dotIndexAfterAt = email.indexOf(".",indexAt +1);
        if (dotIndexAfterAt == -1) return false;

        //3
        int countAfterDot = email.length() - dotIndexAfterAt - 1;
        if (countAfterDot < 2) return false;
        // 3. После последней точки есть 2 или более символов
        // test@fazx.com.ne.t
        //int lastDotIndex = email.lastIndexOf('.');
        //if (lastDotIndex + 2 >= email.length()) return false;

        //4
        if(! hasLettersAndNumerals(email)) return false;
        if(! containsSpecialCharacters(email)) return false;
        /*
         // 4.  Алфавит, цифры, '-', '_', '.', '@'

        Я беру каждый символ. Проверяю, что он не является "запрещенным"
        И если нахожу не подходящий символ - возвращаю false

        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);

            // Если символ удовлетворяет одному из условий на "правильность"
            boolean isPass = (Character.isAlphabetic(ch) ||
                    Character.isDigit(ch) ||
                    ch == '-' ||
                    ch == '_' ||
                    ch == '.' ||
                    ch == '@');

            // Если любой символ НЕ подходящий, сразу возвращаем false
            if (!isPass) return false;


            Равнозначные выражения.
            if (!Character.isAlphabetic(ch) && !Character.isDigit(ch) && ch != '-' && ch != '_' && ch != '.' && ch != '@') return false;
            if (!(Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '-' || ch == '_' || ch == '.' || ch == '@')) return false;

         */

        return true;
    }

    public static boolean hasLettersAndNumerals(String email) {

        boolean hasNumber = false;
        boolean chUp = false;
        boolean chLow = false;

        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);

            if (Character.isUpperCase(ch)){
                chUp = true;
            }else if (Character.isLowerCase(ch)){
                chLow = true;
            }else if (Character.isDigit(ch)) {
                hasNumber = true;
            }
            if (chLow && chUp && hasNumber) {
                return true;
            }
        }
        // error message
        if (!chUp) {
            System.out.println("Insert uppercase letter");
        }
        if (!chLow) {
            System.out.println("Insert lowercase letter");
        }
        if (!hasNumber) {
            System.out.println("Insert numeral");
        }

        return false;
    }

    public static boolean containsSpecialCharacters(String email) {
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (ch == '-' || ch == '_' || ch == '.' || ch == '@' || ch == '%' || ch == '!' || ch == '&' || ch == '*' || ch == ',' || ch == '(' || ch == ')' || ch == '$' || ch == '[' || ch == ']') {
                return true;
            }
        }
/*
String symbols = "!%$@&*()[].,-";
 for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
if (symbols.indexOf(ch) >= 0) isSpecialSymbol = true;
            // if (symbols.contains(String.valueOf(ch))) isSpecialSymbol = true;
 */
        return false;
    }

    public static boolean isPasswordValid(String password){
        boolean b1 = password.length() >= 8 || password == null;
        if (!b1){
            System.out.println("Check pass length");
        }
        boolean b2 = hasLettersAndNumerals(password);
        boolean b3 = containsSpecialCharacters(password);
        if (!b3){
            System.out.println("Insert special symbols");
        }

        return b1 && b2 && b3;
    }

}
