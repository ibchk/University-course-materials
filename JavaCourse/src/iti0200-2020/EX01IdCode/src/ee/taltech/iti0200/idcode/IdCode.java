public class IdCode {

    public static final int LEAP400 = 400;
    public static final int IDLEN = 11;
    public static final int PLACE7 = 7;
    public static final int TARTUEND1 = 20;
    public static final int TARTUSTART1 = 11;
    public static final int DAYLEAP29 = 29;
    public static final int DAYLEAP30 = 30;
    public static final int DAYLEAP31 = 31;
    public static final int TARTUSTART2 = 271;
    public static final int TARTUEND2 = 370;
    public static final int TALLINNSTART1 = 21;
    public static final int TALLINNEND1 = 220;
    public static final int TALLINNSTART2 = 471;
    public static final int TALLINNEND2 = 490;
    public static final int KOHTLASTART = 221;
    public static final int KOHTLAEND = 270;
    public static final int NARVASTART = 371;
    public static final int NARVAEND = 420;
    public static final int PARNUSTART = 421;
    public static final int PARNUEND = 470;
    public static final int PAIDESTART = 491;
    public static final int PAIDEEND = 520;
    public static final int RAKVERESTART = 521;
    public static final int RAKVEREEND = 570;
    public static final int VALGASTART = 571;
    public static final int VALGAEND = 600;
    public static final int VILJANDISTART = 601;
    public static final int VILJANDIEND = 650;
    public static final int VORUSTART = 651;
    public static final int VORUEND = 710;
    public static final int YEARFEMALE21CENTURYCASE = 6;
    public static final int MONTHCHECK = 13;
    public static final int MONTHHALFMAKER = 8;
    public static final int FULLMONTH31DAYS = 32;
    public static final int FORDEFAULTYEAR2022 = 2022;

    private final String idCodeValue;

    enum Gender {
        MALE, FEMALE
    }

    public String getIdCodeValue() {
        return idCodeValue;
    }

    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
    }

    public boolean isCorrect() {
        return isControlNumberCorrect() && isQueueNumberCorrect() && isYearNumberCorrect()
                && isMonthNumberCorrect() && isGenderNumberCorrect() && isDayNumberCorrect();
    }

    public String getInformation() {
        if (idCodeValue.length() == IDLEN && controlCorrectControllers()) {
            String day = idCodeValue.substring(5, PLACE7);
            String monthNumber = idCodeValue.substring(3, 5);
            return "This is a " + getGender() + " born on " + day + "." + monthNumber + "."
                    + getFullYear() + " in " + getBirthPlace();
        }
        return "Given invalid ID code!";
    }

    public Gender getGender() {
        if (isGenderNumberCorrect()) {
            int firstnumber = Character.getNumericValue(idCodeValue.charAt(0));
            if (0 < firstnumber && firstnumber < PLACE7) {
                return (firstnumber % 2 == 1) ? Gender.MALE : Gender.FEMALE;
            }
        }
        return null;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public String getBirthPlace() {
        if (idCodeValue.length() != IDLEN || !isQueueNumberCorrect()) {
            return "Wrong input!";
        }
        int birthplace = Integer.parseInt(idCodeValue.substring(PLACE7, 10));
        if (isBetween(birthplace, 1, 10)) {
            return "Kuressaare";
        } else if (isBetween(birthplace, TARTUSTART1, TARTUEND1) | isBetween(birthplace, TARTUSTART2, TARTUEND2)) {
            return "Tartu";
        } else if (isBetween(birthplace, TALLINNSTART1, TALLINNEND1)
                | isBetween(birthplace, TALLINNSTART2, TALLINNEND2)) {
            return "Tallinn";
        } else if (isBetween(birthplace, KOHTLASTART, KOHTLAEND)) {
            return "Kohtla-Järve";
        } else if (isBetween(birthplace, NARVASTART, NARVAEND)) {
            return "Narva";
        } else if (isBetween(birthplace, PARNUSTART, PARNUEND)) {
            return "Pärnu";
        } else if (isBetween(birthplace, PAIDESTART, PAIDEEND)) {
            return "Paide";
        } else if (isBetween(birthplace, RAKVERESTART, RAKVEREEND)) {
            return "Rakvere";
        } else if (isBetween(birthplace, VALGASTART, VALGAEND)) {
            return "Valga";
        } else if (isBetween(birthplace, VILJANDISTART, VILJANDIEND)) {
            return "Viljandi";
        } else if (isBetween(birthplace, VORUSTART, VORUEND)) {
            return "Võru";
        }
        return null;
    }

    public int getFullYear() {
        if (idCodeValue.length() == IDLEN && isYearNumberCorrect() && isNumeric()) {
            int firstnumber = Character.getNumericValue(idCodeValue.charAt(0));
            if (getGender() != null) {
                switch (firstnumber) {
                    case 1:
                    case 2:
                        return Integer.parseInt("18" + idCodeValue.substring(1, 3));
                    case 3:
                    case 4:
                        return Integer.parseInt("19" + idCodeValue.substring(1, 3));
                    case 5:
                    case YEARFEMALE21CENTURYCASE:
                        return Integer.parseInt("20" + idCodeValue.substring(1, 3));
                    default:
                        return FORDEFAULTYEAR2022;
                }
            }
        }
        return -1;
    }

    private boolean isGenderNumberCorrect() {
        return (Character.isDigit(idCodeValue.charAt(0)))
                && Character.getNumericValue(idCodeValue.charAt(0)) < PLACE7
                && Character.getNumericValue(idCodeValue.charAt(0)) > 0;
    }

    private boolean isYearNumberCorrect() {
        for (int i = 1; i < 3; i++) {
            if (idCodeValue.length() != IDLEN || !(Character.isDigit(idCodeValue.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumeric() {
        if (idCodeValue.length() != IDLEN) {
            return false;
        }
        for (int i = 0; i < idCodeValue.length(); i++) {
            if (!(Character.isDigit(idCodeValue.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMonthNumberCorrect() {
        for (int i = 3; i < 5; i++) {
            if (!(Character.isDigit(idCodeValue.charAt(i)))) {
                return false;
            }
        }
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        return monthNumber < MONTHCHECK;
    }

    private boolean isDayNumberCorrect() {
        if (!isNumeric()) {
            return false;
        }
        int yearNumber = getFullYear();
        int day = Integer.parseInt(idCodeValue.substring(5, PLACE7));
        int month = Integer.parseInt(idCodeValue.substring(3, 5));
        return isNumeric() && isYearNumberCorrect() && isGenderNumberCorrect() && isMonthNumberCorrect()
                && ((month < MONTHHALFMAKER && month % 2 != 0 && day < FULLMONTH31DAYS)
                | (month < MONTHHALFMAKER && month % 2 == 0 && day < DAYLEAP31 && month != 2)
                | (month > PLACE7 && month % 2 == 0 && day < FULLMONTH31DAYS)
                | (month > PLACE7 && month % 2 != 0 && day < DAYLEAP31)
                | (month == 2 && isLeapYear(yearNumber) && day < DAYLEAP30)
                | (month == 2 && !isLeapYear(yearNumber) && day < DAYLEAP29));
    }

    private boolean isQueueNumberCorrect() {
        if (idCodeValue.length() == IDLEN) {
            for (int i = PLACE7; i < 10; i++) {
                if (!(Character.isDigit(idCodeValue.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean controlCorrectControllers() {
        return isQueueNumberCorrect() && isDayNumberCorrect() && isMonthNumberCorrect() && isGenderNumberCorrect()
                && isYearNumberCorrect() && isNumeric() && isControlNumberCorrect();
    }

    private boolean isControlNumberCorrect() {
        if (!isNumeric()) {
            return false;
        }
        String firstControl = "1234567891";
        String secondControl = "3456789123";
        int getNumber = 0;
        for (int i = 0; i < idCodeValue.length() - 1; i++) {
            getNumber += Integer.parseInt(idCodeValue.substring(i, i + 1))
                    * Integer.parseInt(firstControl.substring(i, i + 1));
        }
        if (getNumber % IDLEN == 10) {
            getNumber = 0;
            for (int i = 0; i < idCodeValue.length() - 1; i++) {
                getNumber += Integer.parseInt(idCodeValue.substring(i, i + 1))
                        * Integer.parseInt(secondControl.substring(i, i + 1));
            }
        }
        return getNumber % IDLEN == Integer.parseInt(idCodeValue.substring(10, IDLEN)) || getNumber % IDLEN == 10;
    }

    private boolean isLeapYear(int fullYear) {
        return fullYear != -1 && (fullYear % LEAP400 == 0 || (fullYear % 4 == 0 & fullYear % 100 != 0));
    }

    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("39912242226");
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
    }
}
