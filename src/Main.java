import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
public class Main {
    static String[] arrRooms = new String[10];
    static int[] safeStartDaysArr = new int[100];
    static int[] safeStartMonthsArr = new int[100];
    static int[] safeStartYearsArr = new int[100];
    static int[] safeEndDaysArr = new int[100];
    static int[] safeEndMonthsArr = new int[100];
    static int[] safeEndYearsArr = new int[100];
    static int[] numberOfBedsArr = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3};
    static int roomNumberReserve;
    static String [] additionalServices = new String [10];

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

    public static void makeAnEmptyRoom(String[] arrRooms) {
        for (int i = 0; i < arrRooms.length; i++) {
            arrRooms[i] = "empty";
        }
    }

    public static void checkForEmptyRooms(String[] arrRooms) throws ParseException {
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

    public static void makeAReservation(String[] arrRooms) throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please write the room's number you want to reserve(1-10): ");
        roomNumberReserve = scan.nextInt();
        if (roomNumberReserve < 1 || roomNumberReserve > 10) {
            System.out.println("-----------------------------------------------------");
            System.out.println("INVALID INPUT! THERE ISN'T A ROOM WITH WHIT NUMBER!");
            System.out.println("Now enter the information again!");
            System.out.println("-----------------------------------------------------");
            makeAReservation(arrRooms);
            return;
        }
        makeAReservationDate(arrRooms);

        arrRooms[roomNumberReserve - 1] = "reserved";
    }

    public static void makeAReservationDate(String[] arrRooms) throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please write the start of the reservation! ");
        System.out.print("Enter the start day: ");
        int startDate = scan.nextInt();
        System.out.print("Enter the month: ");
        int startMonth = scan.nextInt();
        if (startMonth < 1 || startMonth > 12) {
            System.out.print("INVALID MONTH INPUT! Now enter the month again!");
            startMonth = scan.nextInt();
        }
        System.out.print("Enter the year: ");
        int startYear = scan.nextInt();
        System.out.println("Please write the end of the reservation! ");
        System.out.print("Enter the end day: ");
        int endDate = scan.nextInt();
        System.out.print("Enter the month: ");
        int endMonth = scan.nextInt();
        System.out.print("Enter the year: ");
        int endYear = scan.nextInt();
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
                    System.out.println("  INVALID DATE INPUT!");
                    System.out.println("This room is already reserved on this dates!");
                    System.out.println("================================================");
                    continue1 = false; break;
                }
            }
        }
        if (continue1 == true) {
            if ((startYear > endYear) || (startYear == endYear && startMonth > endMonth) || (startYear == endYear && startMonth == endMonth && startDate > endDate)) {
                System.out.println("----------------------------");
                System.out.println("    INVALID DATE INPUT!");
                System.out.println("----------------------------");
                System.out.println("Now enter the dates again: ");
                makeAReservationDate(arrRooms);
            } else {
                boolean execute = false;
                for (int i = -1; i < 90; i += 10) {
                    execute = safeStartDaysArr[roomNumberReserve - 1] == 0 || i == 9;
                    if (execute) {
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
                System.out.println("Enter some information about the user: ");
                String information = scan.nextLine();
                information = scan.nextLine();
                System.out.println("Room No:" + roomNumberReserve + " is successfully reserved");
            }
        }
    }

    public static void cancelAReservation(String[] arrRooms) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please write the number of the room whose reservation you want to cancel: ");
        int roomNumberCancel = scan.nextInt();
        if (arrRooms[roomNumberCancel - 1] == "reserved") {
            switch (roomNumberCancel) {
                case 1 -> arrRooms[0] = "empty";
                case 2 -> arrRooms[1] = "empty";
                case 3 -> arrRooms[2] = "empty";
                case 4 -> arrRooms[3] = "empty";
                case 5 -> arrRooms[4] = "empty";
                case 6 -> arrRooms[5] = "empty";
                case 7 -> arrRooms[6] = "empty";
                case 8 -> arrRooms[7] = "empty";
                case 9 -> arrRooms[8] = "empty";
                case 10 -> arrRooms[9] = "empty";
            }
            System.out.println("Room No:" + roomNumberCancel + " is " + arrRooms[roomNumberCancel - 1] + " now.");
        } else {
            System.out.println("This room is already empty!!!");
            System.out.println("Do you still want to cancel a reservation?(Type true for yes or false for no!)");
            boolean continueWithCanceling = scan.nextBoolean();
            if (continueWithCanceling == true) {
                System.out.println("OK! Now enter the information again!");
                cancelAReservation(arrRooms);
            }
        }
    }

    public static void printStats(int[] safeStartDaysArr) throws ParseException {
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
        System.out.println("---------------------------------------------");
        System.out.println("                  STATS                      ");
        System.out.println("---------------------------------------------");
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
        System.out.println("Room No 1: " + (arrDiffDays[0] + arrDiffDays[10] + arrDiffDays[20] + arrDiffDays[30] + arrDiffDays[40] + arrDiffDays[50] + arrDiffDays[60] + arrDiffDays[70] + arrDiffDays[80] + arrDiffDays[90]));
        System.out.println("Room No 2: " + (arrDiffDays[1] + arrDiffDays[11] + arrDiffDays[21] + arrDiffDays[31] + arrDiffDays[41] + arrDiffDays[51] + arrDiffDays[61] + arrDiffDays[71] + arrDiffDays[81] + arrDiffDays[91]));
        System.out.println("Room No 3: " + (arrDiffDays[2] + arrDiffDays[12] + arrDiffDays[22] + arrDiffDays[32] + arrDiffDays[42] + arrDiffDays[52] + arrDiffDays[62] + arrDiffDays[72] + arrDiffDays[82] + arrDiffDays[92]));
        System.out.println("Room No 4: " + (arrDiffDays[3] + arrDiffDays[13] + arrDiffDays[23] + arrDiffDays[33] + arrDiffDays[43] + arrDiffDays[53] + arrDiffDays[63] + arrDiffDays[73] + arrDiffDays[83] + arrDiffDays[93]));
        System.out.println("Room No 5: " + (arrDiffDays[4] + arrDiffDays[14] + arrDiffDays[24] + arrDiffDays[34] + arrDiffDays[44] + arrDiffDays[54] + arrDiffDays[64] + arrDiffDays[74] + arrDiffDays[84] + arrDiffDays[94]));
        System.out.println("Room No 6: " + (arrDiffDays[5] + arrDiffDays[15] + arrDiffDays[25] + arrDiffDays[35] + arrDiffDays[45] + arrDiffDays[55] + arrDiffDays[65] + arrDiffDays[75] + arrDiffDays[85] + arrDiffDays[95]));
        System.out.println("Room No 7: " + (arrDiffDays[6] + arrDiffDays[16] + arrDiffDays[26] + arrDiffDays[36] + arrDiffDays[46] + arrDiffDays[56] + arrDiffDays[66] + arrDiffDays[76] + arrDiffDays[86] + arrDiffDays[96]));
        System.out.println("Room No 8: " + (arrDiffDays[7] + arrDiffDays[17] + arrDiffDays[27] + arrDiffDays[37] + arrDiffDays[47] + arrDiffDays[57] + arrDiffDays[67] + arrDiffDays[77] + arrDiffDays[87] + arrDiffDays[97]));
        System.out.println("Room No 9: " + (arrDiffDays[8] + arrDiffDays[18] + arrDiffDays[28] + arrDiffDays[38] + arrDiffDays[48] + arrDiffDays[58] + arrDiffDays[68] + arrDiffDays[78] + arrDiffDays[88] + arrDiffDays[98]));
        System.out.println("Room No 10: " + (arrDiffDays[9] + arrDiffDays[19] + arrDiffDays[29] + arrDiffDays[39] + arrDiffDays[49] + arrDiffDays[59] + arrDiffDays[69] + arrDiffDays[79] + arrDiffDays[89] + arrDiffDays[99]));
    }

    public static void findARoom() throws ParseException {
        Scanner scan = new Scanner(System.in);
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
        System.out.println("--------------------------------");
        System.out.println("AVAILABLE ROOMS WITH "+ beds + " BED/S");
        System.out.println("--------------------------------");
        for (int i = 0; i < numberOfBedsArr.length; i++) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            d1 = format.parse(safeStartDaysArr[i] + "/" + safeStartMonthsArr[i] + "/" + safeStartYearsArr[i]);
            d2 = format.parse(safeEndDaysArr[i] + "/" + safeEndMonthsArr[i] + "/" + safeEndYearsArr[i]);
            d1Check = format.parse(startDate + "/" + startMonth + "/" + startYear);
            d2Check = format.parse(endDate + "/" + endMonth + "/" + endYear);
            if (beds == numberOfBedsArr[i]) {
                if (safeStartDaysArr[i] == 0) {
                    System.out.println("Room No " + (i + 1));
                } else if (d1Check.compareTo(d1) <= 0 && d2Check.compareTo(d2) >= 0) {
                    diff = d2.getTime() - d1.getTime();
                    diffDays = diff / (24 * 60 * 60 * 1000);
                    if (diffDays < 0) {
                        System.out.println("Room No " + (i + 1));
                    }
                } else if (d1Check.compareTo(d1) >= 0 && d2Check.compareTo(d2) <= 0) {
                    diff = d2Check.getTime() - d1Check.getTime();
                    diffDays = diff / (24 * 60 * 60 * 1000);
                    if (diffDays < 0) {
                        System.out.println("Room No " + (i + 1));
                    }
                } else if (d1.compareTo(d1Check) <= 0 && d2.compareTo(d2Check) <= 0) {
                    diff = d2.getTime() - d1Check.getTime();
                    diffDays = diff / (24 * 60 * 60 * 1000);
                    if (diffDays < 0) {
                        System.out.println("Room No " + (i + 1));
                    }
                } else if (d1Check.compareTo(d1) <= 0 && d2Check.compareTo(d2) <= 0) {
                    diff = d2Check.getTime() - d1.getTime();
                    diffDays = diff / (24 * 60 * 60 * 1000);
                    if (diffDays < 0) {
                        System.out.println("Room No " + (i + 1));
                    }
                }
            }
        }
    }

    public static void updateARoom () throws ParseException {
        Scanner scan = new Scanner(System.in);
        System.out.println("THESE ARE THE ADDITIONAL SERVICES OFFERED BY OUR HOTEL");
        System.out.println("1. Baby cot");
        System.out.println("2. With breakfast");
        System.out.println("3. Three-meal courses");
        System.out.println("4. Overlooking the sea");
        System.out.println("5. Overlooking the mountain");
        System.out.println("6. Overlooking the courtyard");
        System.out.print("Please, enter your choice here-> ");
        byte addChoice = scan.nextByte();
        switch (addChoice){
            case 1 -> {
                System.out.println("YOU CHOSE -> BABY COT");
                System.out.println("Now you can make the reservation");
                makeAReservation(arrRooms);
                additionalServices[roomNumberReserve-1] = "baby cot";
            }
            case 2 ->{
                System.out.println("YOU CHOSE -> RESERVATION WITH BREAKFAST");
                System.out.println("Now you can make the reservation");
                makeAReservation(arrRooms);
                additionalServices[roomNumberReserve-1] = "with breakfast";
            }
            case 3 ->{
                System.out.println("YOU CHOSE -> RESERVATION WITH THREE-MEAL COURSES");
                System.out.println("Now you can make the reservation");
                makeAReservation(arrRooms);
                additionalServices[roomNumberReserve-1] = "with three meal courses";
            }
            case 4 ->{
                System.out.println("YOU CHOSE -> RESERVATION OVERLOOKING THE SEA");
                System.out.println("Now you can make the reservation");
                makeAReservation(arrRooms);
                additionalServices[roomNumberReserve-1] = "overlooking the sea";
            }
            case 5 ->{
                System.out.println("YOU CHOSE -> RESERVATION OVERLOOKING THE MOUNTAIN");
                System.out.println("Now you can make the reservation");
                makeAReservation(arrRooms);
                additionalServices[roomNumberReserve-1] = "overlooking the mountain";
            }
            case 6 ->{
                System.out.println("YOU CHOSE -> RESERVATION OVERLOOKING THE COURTYARD");
                System.out.println("Now you can make the reservation");
                makeAReservation(arrRooms);
                additionalServices[roomNumberReserve-1] = "overlooking the courtyard";
            }
            default -> {
                System.out.println("INVALID INPUT!");
                System.out.println("Now enter the information again!");
                updateARoom();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        Scanner scan = new Scanner(System.in);
        printWelcomeMessage();
        makeAnEmptyRoom(arrRooms);
        boolean askIfTheUserWantsToContinue;
        do {
            printTheSections();
            System.out.print("Enter your selection here: ");
            int selection = scan.nextInt();
            switch (selection) {
                case 1 -> makeAReservation(arrRooms);
                case 2 -> checkForEmptyRooms(arrRooms);
                case 3 -> cancelAReservation(arrRooms);
                case 4 -> printStats(safeStartDaysArr);
                case 5 -> findARoom();
                case 6 -> updateARoom();
                default -> {
                    System.out.println("----------------------------------");
                    System.out.println("INVALID SELECTION INPUT!");
                    System.out.println("Now enter your selection again!");
                    System.out.println("----------------------------------");
                }
            }
            askIfTheUserWantsToContinue = askIfTheUserWantsToContinue();
        } while (askIfTheUserWantsToContinue == true);
    }
}