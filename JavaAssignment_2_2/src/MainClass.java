public class MainClass {
    public static void main(String[] args) {
        String str1 = "Khalid";
        String str2 = "Mohammed";
        String longer = StringUtils.betterString(str1, str2, (s1, s2) -> s1.length() > s2.length());
        String first = StringUtils.betterString(str1, str2, (s1, s2) -> true);

        System.out.println("Longer String : " + longer);
        System.out.println("First String : " + first);

        String str3 = "Ahmed32";
        System.out.println("Does \'" + str2 + "\' contain letters only ?");
        System.out.println("Answer : " + StringUtils.checkString(str2, Character::isLetter));
        System.out.println("Does \'" + str3 + "\' contain letters only ?");
        System.out.println("Answer : " + StringUtils.checkString(str3, Character::isLetter));
    }
}
