import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Main {
    static int[] safeStartDaysArr = new int[100];
    static int[] safeStartMonthsArr = new int[100];
    static int[] safeStartYearsArr = new int[100];
    static int[] safeEndDaysArr = new int[100];
    static int[] safeEndMonthsArr = new int[100];
    static int[] safeEndYearsArr = new int[100];
    static int[] numberOfBedsArr = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3};
    static int roomNumberReserve;
    static int startDate;
    static int startMonth;
    static int startYear;

    static int endDate;

    static int endMonth;

    static int endYear;
    static String [] additionalServices = new String [100];

    public static void printWelcomeMessage() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("                                       WELCOME TO HAPPY HOTEL!");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }
    public static void printTheSections() {
        System.out.println("Please select what you want to do(1-6): ");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("1. Make a reservation.");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("2. Free list rooms.");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("3. Checkout room.");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("4. Stats.");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("5. Find a room.");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("6. Update a room.");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public static boolean askIfTheUserWantsToContinue() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to continue the program(true or false)? ");
        boolean continueTheProgram;
        continueTheProgram = scan.nextBoolean();
        if (continueTheProgram == false) {
            System.out.println("Goodbye! :)");
            System.exit(0);
        }
        return continueTheProgram;
    }


    public static void checkForEmptyRooms() throws ParseException {
        System.out.println("===========================================");
        System.out.println("      CHECK FOR CURRENTLY FREE ROOMS");
        System.out.println("===========================================");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        Date d1, d2;
        for(int i=0; i<10; i++) {
            String roomCheckNow="empty";
            for(int j= i; j<100; j+=10) {
                if (safeStartDaysArr[j] != 0) {
                    d1 = format.parse(safeStartDaysArr[j] + "/" + safeStartMonthsArr[j] + "/" + safeStartYearsArr[j]);
                    d2 = format.parse(safeEndDaysArr[j] + "/" + safeEndMonthsArr[j] + "/" + safeEndYearsArr[j]);
                    if (date.compareTo(d1) >= 0 && date.compareTo(d2) <= 0) {
                        roomCheckNow = "reserved";
                        break;
                    }
                } else {
                    System.out.println("Room No: "+ (i+1) +" is currently -> " + roomCheckNow); break;
                }
            }
        }
    }

    public static void makeAReservation() throws ParseException {
        System.out.println("=========================================");
        System.out.println("           MAKE A RESERVATION");
        System.out.println("=========================================");
        Scanner scan = new Scanner(System.in);
        System.out.print("Please write the room's number you want to reserve(1-10): ");
        roomNumberReserve = scan.nextInt();
        if (roomNumberReserve < 1 || roomNumberReserve > 10) {
            System.out.println("-----------------------------------------------------");
            System.out.println("INVALID INPUT! THERE ISN'T A ROOM WITH WHIT NUMBER!");
            System.out.println("Now enter the information again!");
            System.out.println("-----------------------------------------------------");
            makeAReservation();
            return;
        }
        makeAReservationDate();
    }

    public static boolean checkStartDatesForReservationDate(){
        boolean result = true;
        if(startMonth<1 || startMonth>12 || startDate<1 || startDate>31){
            result=false;
            return false;
        }
        if(startMonth==4 || startMonth==6 || startMonth==9 || startMonth==11){
            if(startDate>=31){
              return false;
            }
        }
        if(startMonth==2 && startDate>=29) {
            boolean isLeap = false;
            if (startYear % 4 == 0) {
                if (startYear % 100 == 0) {
                    if (startYear % 400 == 0) {
                        isLeap = true;
                    }
                } else {
                    isLeap = true;
                }
            }
            if (isLeap == false && startMonth == 2 && startDate > 28) {
                result = false;
                return false;
            }
        }
        return result;
    }
    public static void enterAndCheckTheStartDatesOfTheReservation(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please write the start of the reservation! ");
        System.out.print("Enter the start day: ");
        startDate = scan.nextInt();
        System.out.print("Enter the month: ");
        startMonth = scan.nextInt();
        System.out.print("Enter the year: ");
        startYear = scan.nextInt();
        boolean check = checkStartDatesForReservationDate();
        while(check==false){
            System.out.println("--------------------------------");
            System.out.println("   INVALID START DATE INPUT!");
            System.out.println("--------------------------------");
            System.out.println("Now enter the dates again: ");
            enterAndCheckTheStartDatesOfTheReservation();
            checkStartDatesForReservationDate();
            check = checkStartDatesForReservationDate();
        }
    }
    public static void enterAndCheckTheEndDatesOfTheReservation(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please write the end of the reservation! ");
        System.out.print("Enter the end day: ");
        endDate = scan.nextInt();
        System.out.print("Enter the month: ");
        endMonth = scan.nextInt();
        System.out.print("Enter the year: ");
        endYear = scan.nextInt();
        boolean check = checkEndDatesForReservationDate();
        while(check==false){
            System.out.println("--------------------------------");
            System.out.println("   INVALID END DATE INPUT!");
            System.out.println("--------------------------------");
            System.out.println("Now enter the dates again: ");
            enterAndCheckTheEndDatesOfTheReservation();
            checkEndDatesForReservationDate();
            check = checkEndDatesForReservationDate();
        }
    }
    public static boolean checkEndDatesForReservationDate(){
        boolean result = true;
        if(endMonth<1 || endMonth>12 || endDate<1 || endDate>31){
            result=false;
            return false;
        }
        if(endMonth==4 || endMonth==6 || endMonth==9 || endMonth==11){
            if(endDate>=31){
                return false;
            }
        }
        boolean isLeap = false;
        if (endYear % 4 == 0) {
            if (endYear % 100 == 0) {
                if (endYear % 400 == 0) {
                    isLeap = true;
                }
            } else {
                isLeap = true;
            }
        }
        if(isLeap==false && endMonth==2 && endDate>28){
            result=false;
            return false;
        }
        return result;
    }
    public static void makeAReservationDate() throws ParseException {
        Scanner scan = new Scanner(System.in);
        enterAndCheckTheStartDatesOfTheReservation();
        enterAndCheckTheEndDatesOfTheReservation();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = format.parse(startDate + "/" + startMonth + "/" + startYear);
        Date d2 = format.parse(endDate + "/" + endMonth + "/" + endYear);
        boolean continue1 = true;
        for (int i = roomNumberReserve - 1; i < 100; i += 10) {
            if (safeStartDaysArr[i] != 0) {
                Date d3 = format.parse(safeStartDaysArr[i] + "/" + safeStartMonthsArr[i] + "/" + safeStartYearsArr[i]);
                Date d4 = format.parse(safeEndDaysArr[i] + "/" + safeEndMonthsArr[i] + "/" + safeEndYearsArr[i]);
                if ((d1.compareTo(d3)<0 && d1.compareTo(d4)<0 && d2.compareTo(d3)<0 && d2.compareTo(d4)<0) || (d1.compareTo(d3)>0 && d1.compareTo(d4)>0 && d2.compareTo(d3)>0 && d2.compareTo(d4)>0)) {
                    continue1 = true;
                } else {
                    System.out.println("================================================");
                    System.out.println("             INVALID DATE INPUT!");
                    System.out.println("This room is already reserved on this dates!");
                    System.out.println("================================================");
                    continue1 = false;
                }
            }
        }
        if (continue1 == true) {
            if ((startYear > endYear) || (startYear == endYear && startMonth > endMonth) || (startYear == endYear && startMonth == endMonth && startDate > endDate)) {
                System.out.println("----------------------------");
                System.out.println("    INVALID DATE INPUT!");
                System.out.println("----------------------------");
                System.out.println("Now enter the dates again: ");
                makeAReservationDate();
            } else {
                for (int i = -1; i < 90; i += 10) {
                    if (safeStartDaysArr[roomNumberReserve+i]==0) {
                        safeStartDaysArr[roomNumberReserve + i] = startDate;
                        safeStartMonthsArr[roomNumberReserve + i] = startMonth;
                        safeStartYearsArr[roomNumberReserve + i] = startYear;
                        safeEndDaysArr[roomNumberReserve + i] = endDate;
                        safeEndMonthsArr[roomNumberReserve + i] = endMonth;
                        safeEndYearsArr[roomNumberReserve + i] = endYear;
                        break;
                    }
                }
                System.out.println("The start day is: " + startDate + "/" + startMonth + "/" + startYear + "   12:00h.");
                System.out.println("The end day is: " + endDate + "/" + endMonth + "/" + endYear + "   12:00h.");
                System.out.println("Enter some information about the costumer: ");
                String information = scan.nextLine();
                System.out.println("Room No:" + roomNumberReserve + " is successfully reserved");
            }
        }
    }

    public static void cancelAReservation() {
        System.out.println("=============================================");
        System.out.println("           CANCEL A RESERVATION");
        System.out.println("=============================================");
        Scanner scan = new Scanner(System.in);
        System.out.print("Please write the number of the room whose reservation you want to cancel(1-10): ");
        int roomNumberCancel = scan.nextInt();
        if (roomNumberCancel < 1 || roomNumberCancel > 10) {
            System.out.println("Invalid room number input! The rooms are with numbers from 1 to 10!");
            System.out.println("Now enter the number again!");
            roomNumberCancel = scan.nextInt();
        }
        System.out.println("These are the reservations for this rooms: ");
        int br = 1;
        int i = (roomNumberCancel - 1);
        if (safeStartDaysArr[i] != 0) {
            while (safeStartDaysArr[i] != 0 && i < safeStartDaysArr.length) {
                System.out.println("Reservation No: " + br + "  ----->   from: " + safeStartDaysArr[i] + "/" + safeStartMonthsArr[i] + "/" + safeStartYearsArr[i] + "   to: " + safeEndDaysArr[i] + "/" + safeEndMonthsArr[i] + "/" + safeEndYearsArr[i]);
                br++;
                i += 10;
            }
            System.out.print("Please write the reservation number you want to cancel: ");
            int numberOfReservation = scan.nextInt();
            safeStartDaysArr[i - 10] = 0;
            safeStartMonthsArr[i - 10] = 0;
            safeStartYearsArr[i - 10] = 0;
            safeEndDaysArr[i - 10] = 0;
            safeEndMonthsArr[i - 10] = 0;
            safeEndYearsArr[i - 10] = 0;
            System.out.println();
            System.out.println("THE RESERVATION IS SUCCESSFULLY CANCELED!");
            System.out.println();
        } else{
            System.out.println("-----------------------------------------------");
            System.out.println("   There are no reservations for this room!");
            System.out.println("-----------------------------------------------");
        }
    }

    public static void printStats() throws ParseException {
        System.out.println("=============================================");
        System.out.println("                  STATS                      ");
        System.out.println("=============================================");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the start day: ");
        int startDateCheck = scan.nextInt();
        System.out.print("Enter the start month: ");
        int startMonthCheck = scan.nextInt();
        System.out.print("Enter the start year: ");
        int startYearCheck = scan.nextInt();
        System.out.print("Enter the end day: ");
        int endDateCheck = scan.nextInt();
        System.out.print("Enter the end month: ");
        int endMonthCheck = scan.nextInt();
        System.out.print("Enter the end year: ");
        int endYearCheck = scan.nextInt();
        long diff = 0;
        long diffDays = 0;
        int[] arrDiffDays = new int[100];
        for (int j = 0; j < 100; j++) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = format.parse(safeStartDaysArr[j] + "/" + safeStartMonthsArr[j] + "/" + safeStartYearsArr[j]);
            Date d2 = format.parse(safeEndDaysArr[j] + "/" + safeEndMonthsArr[j] + "/" + safeEndYearsArr[j]);
            Date d1Check = format.parse(startDateCheck + "/" + startMonthCheck + "/" + startYearCheck);
            Date d2Check = format.parse(endDateCheck + "/" + endMonthCheck + "/" + endYearCheck);
            if (safeStartDaysArr[j] == 0) {
                arrDiffDays[j] = 0;
            } else if (d1Check.compareTo(d1) <= 0 && d2Check.compareTo(d2) >= 0) {
                diff = d2.getTime() - d1.getTime();
                diffDays = diff / (24 * 60 * 60 * 1000);
                if (diffDays > 0) {
                    arrDiffDays[j] += diffDays + 1;
                } else {
                    arrDiffDays[j] = 0;
                }
            } else if (d1Check.compareTo(d1) >= 0 && d2Check.compareTo(d2) <= 0) {
                diff = d2Check.getTime() - d1Check.getTime();
                diffDays = diff / (24 * 60 * 60 * 1000);
                if (diffDays > 0) {
                    arrDiffDays[j] += diffDays + 1;
                } else {
                    arrDiffDays[j] = 0;
                }
            } else if (d1.compareTo(d1Check) <= 0 && d2.compareTo(d2Check) <= 0) {
                diff = d2.getTime() - d1Check.getTime();
                diffDays = diff / (24 * 60 * 60 * 1000);
                if (diffDays > 0) {
                    arrDiffDays[j] += diffDays + 1;
                } else {
                    arrDiffDays[j] = 0;
                }
            } else if (d1Check.compareTo(d1) <= 0 && d2Check.compareTo(d2) <= 0) {
                diff = d2Check.getTime() - d1.getTime();
                diffDays = diff / (24 * 60 * 60 * 1000);
                if (diffDays > 0) {
                    arrDiffDays[j] += diffDays + 1;
                } else {
                    arrDiffDays[j] = 0;
                }
            }
        }
        printTheNumberOfUsedDaysFotEachRoom(arrDiffDays);
    }
    public static void printTheNumberOfUsedDaysFotEachRoom(int [] arrDiffDays){
        int sumDays = 0;
        for(int i=0; i<10; i++){
            for(int j=i; j<100; j+=10){
                sumDays+=arrDiffDays[j];
            }
            System.out.println("Room No: "+(i+1)+" --> "+sumDays);
            sumDays=0;
        }
    }

    public static void findARoom() throws ParseException {
        System.out.println("===========================================");
        System.out.println("               FIND A ROOM");
        System.out.println("===========================================");
        Scanner scan = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Enter the number of beds(1-3): ");
        byte beds = scan.nextByte();
        System.out.print("Enter start date: ");
        int startDate = scan.nextInt();
        System.out.print("Enter the start month: ");
        int startMonth = scan.nextInt();
        System.out.print("Enter the start year: ");
        int startYear = scan.nextInt();
        System.out.print("Enter the end day: ");
        int endDate = scan.nextInt();
        System.out.print("Enter the end month: ");
        int endMonth = scan.nextInt();
        System.out.print("Enter the end year: ");
        int endYear = scan.nextInt();
        Date d1 = null;
        Date d2 = null;
        Date d1Check = null;
        Date d2Check = null;
        long diff = 0;
        long diffDays = 0;
        int[] arr = new int[100];
        System.out.println("--------------------------------");
        System.out.println("AVAILABLE ROOMS WITH " + beds + " BED/S");
        System.out.println("--------------------------------");
        for (int i = 0; i < 10; i++) {
            if (beds == numberOfBedsArr[i]) {
                for (int j = i; j < 100; j += 10) {
                    if (safeStartDaysArr[j] == 0) {
                        arr[j] = 1;
                    } else {
                        d1 = format.parse(safeStartDaysArr[j] + "/" + safeStartMonthsArr[j] + "/" + safeStartYearsArr[j]);
                        d2 = format.parse(safeEndDaysArr[j] + "/" + safeEndMonthsArr[j] + "/" + safeEndYearsArr[j]);
                        d1Check = format.parse(startDate + "/" + startMonth + "/" + startYear);
                        d2Check = format.parse(endDate + "/" + endMonth + "/" + endYear);
                        if (d1Check.compareTo(d1) <= 0 && d2Check.compareTo(d2) >= 0) {
                            diff = d2.getTime() - d1.getTime();
                            diffDays = diff / (24 * 60 * 60 * 1000);
                            if (diffDays < 0) {
                                arr[j] = 1;
                            }
                        } else if (d1Check.compareTo(d1) >= 0 && d2Check.compareTo(d2) <= 0) {
                            diff = d2Check.getTime() - d1Check.getTime();
                            diffDays = diff / (24 * 60 * 60 * 1000);
                            if (diffDays < 0) {
                                arr[j] = 1;
                            }
                        } else if (d1.compareTo(d1Check) <= 0 && d2.compareTo(d2Check) <= 0) {
                            diff = d2.getTime() - d1Check.getTime();
                            diffDays = diff / (24 * 60 * 60 * 1000);
                            if (diffDays < 0) {
                                arr[j] = 1;
                            }
                        } else if (d1Check.compareTo(d1) <= 0 && d2Check.compareTo(d2) <= 0) {
                            diff = d2Check.getTime() - d1.getTime();
                            diffDays = diff / (24 * 60 * 60 * 1000);
                            if (diffDays < 0) {
                                arr[j] = 1;
                            }
                        }
                    }
                }
            }
        }
        printFoundRooms(beds, arr);
    }
    public static void printFoundRooms(int beds, int arr[]){
        boolean isRoomReserved = false;
        for(int i=0; i<10; i++) {
            if (beds == numberOfBedsArr[i]) {
                isRoomReserved = false;
                for (int j = i; j < arr.length; j += 10) {
                    if (arr[j] != 1) {
                        isRoomReserved = true;
                        break;
                    }
                }
                if (isRoomReserved == false) {
                    System.out.println("Room No: " + (i+1));
                }
            }
        }
    }

    public static void printTheSelectionsForUpdateARoom() {
        System.out.println("=========================================");
        System.out.println("             UPDATE A ROOM");
        System.out.println("=========================================");
        System.out.println("THESE ARE THE ADDITIONAL SERVICES OFFERED BY OUR HOTEL");
        System.out.println("1. Baby cot");
        System.out.println("2. With breakfast");
        System.out.println("3. Three-meal courses");
        System.out.println("4. Overlooking the sea");
        System.out.println("5. Overlooking the mountain");
        System.out.println("6. Overlooking the courtyard");
    }
    public static void updateARoom () throws ParseException {
        Scanner scan = new Scanner(System.in);
        printTheSelectionsForUpdateARoom();
        System.out.print("Please, enter your choice here(1-6) -> ");
        byte addChoice = scan.nextByte();
        switch (addChoice){
            case 1 -> {
                makeAReservationForBabyCot();
            }
            case 2 ->{
                makeAReservationWithBreakfast();
            }
            case 3 ->{
                makeAReservationWithBreakfastAndDinner();
            }
            case 4 ->{
                makeAReservationOverLookingTheSea();
            }
            case 5 ->{
                makeAReservationOverlookingTheMountain();
            }
            case 6 ->{
                makeAReservationOverlookingTheCourtyard();
            }
            default -> {
                System.out.println("        INVALID INPUT!");
                System.out.println("Now enter the information again!");
                updateARoom();
            }
        }
    }
    public static void makeAReservationForBabyCot() throws ParseException {
        System.out.println("*********************************");
        System.out.println("     YOU CHOSE -> BABY COT");
        System.out.println("*********************************");
        makeAReservation();
        for(int i=roomNumberReserve; i<100; i+=10){
            if(safeStartDaysArr[i]==0) {
                additionalServices[i] = " baby cot ";
            }
        }
    }
    public static void makeAReservationWithBreakfast() throws ParseException {
        System.out.println("***********************************************");
        System.out.println("  YOU CHOSE -> RESERVATION WITH BREAKFAST");
        System.out.println("************************************************");
        makeAReservation();
        for(int i=roomNumberReserve; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " breakfast ";
            }
        }
    }
    public static void makeAReservationWithBreakfastAndDinner() throws ParseException {
        System.out.println("******************************************************");
        System.out.println("   YOU CHOSE -> RESERVATION BREAKFAST AND DINNER ");
        System.out.println("******************************************************");
        makeAReservation();
        for(int i=roomNumberReserve; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " breakfast and dinner ";
            }
        }
    }
    public static void makeAReservationOverLookingTheSea() throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("*******************************************");
        System.out.println("    YOU CHOSE -> OVERLOOKING THE SEA");
        System.out.println("    ROOMS NO: 1,5,7,10 HAVE A SEA VIEW");
        System.out.println("*******************************************");
        makeAReservation();
        while(roomNumberReserve!=1 && roomNumberReserve!=5 && roomNumberReserve!=7 && roomNumberReserve!=10){
            System.out.println("                    INVALID INPUT!");
            System.out.println("            THE RESERVATION IS CANCELED");
            System.out.println("        ROOMS NO: 1,5,7,10 HAVE A SEA VIEW");
            System.out.println("ENTER THE INFORMATION ABOUT THE RESERVATION AGAIN!");
            cancelTheWrongReservationForUpdateARoom();
            makeAReservation();
        }
        for(int i=roomNumberReserve; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " sea view ";
            }
        }
    }
    public static void makeAReservationOverlookingTheMountain() throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("*******************************************");
        System.out.println("    YOU CHOSE -> OVERLOOKING THE MOUNTAIN");
        System.out.println("     ROOMS NO: 2,3 HAVE A MOUNTAIN VIEW");
        System.out.println("*******************************************");
        makeAReservation();
        while(roomNumberReserve!=2 && roomNumberReserve!=3){
            System.out.println("                    INVALID INPUT!");
            System.out.println("            THE RESERVATION IS CANCELED");
            System.out.println("         ROOMS NO: 2,4 HAVE A MOUNTAIN VIEW");
            System.out.println("ENTER THE INFORMATION ABOUT THE RESERVATION AGAIN!");
            cancelTheWrongReservationForUpdateARoom();
            makeAReservation();
        }
        for(int i=roomNumberReserve; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " mountain view ";
            }
        }
    }

    public static void makeAReservationOverlookingTheCourtyard() throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("*********************************************");
        System.out.println("    YOU CHOSE -> OVERLOOKING THE COURTYARD");
        System.out.println("   ROOMS NO: 4,6,8,9 HAVE A COURTYARD VIEW");
        System.out.println("*********************************************");
        makeAReservation();
        while(roomNumberReserve!=4 && roomNumberReserve!=6 && roomNumberReserve!=8 && roomNumberReserve!=9){
            System.out.println("               INVALID INPUT!");
            System.out.println("         THE RESERVATION IS CANCELED");
            System.out.println("     ROOMS NO: 4,6,8,9 HAVE A COURTYARD VIEW");
            System.out.println("ENTER THE INFORMATION ABOUT THE RESERVATION AGAIN!");
            cancelTheWrongReservationForUpdateARoom();
            makeAReservation();
        }
        for(int i=roomNumberReserve; i<100; i+=10){
            if(safeStartDaysArr[i]==0){
                additionalServices[i] = " courtyard view ";
            }
        }
    }
    public static void cancelTheWrongReservationForUpdateARoom(){
        for(int i=roomNumberReserve-1; i<100; i+=10){
            if(safeStartDaysArr[i]!=0 && safeStartDaysArr[i+1]==0){
                safeStartDaysArr[i]=0;
                safeStartMonthsArr[i]=0;
                safeStartYearsArr[i]=0;
                safeEndDaysArr[i]=0;
                safeEndMonthsArr[i]=0;
                safeEndYearsArr[i]=0;
            }
        }
    }
    public static void main(String[] args) throws ParseException {
        Scanner scan = new Scanner(System.in);
        printWelcomeMessage();
        boolean askIfTheUserWantsToContinue;
        do {
            printTheSections();
            System.out.print("Enter your selection here: ");
            int selection = scan.nextInt();
            switch (selection) {
                case 1 -> makeAReservation();
                case 2 -> checkForEmptyRooms();
                case 3 -> cancelAReservation();
                case 4 -> printStats();
                case 5 -> findARoom();
                case 6 -> updateARoom();
                default -> {
                    System.out.println("----------------------------------");
                    System.out.println("    INVALID SELECTION INPUT!");
                    System.out.println("----------------------------------");
                }
            }
            askIfTheUserWantsToContinue = askIfTheUserWantsToContinue();
        } while (askIfTheUserWantsToContinue == true);
    }
}