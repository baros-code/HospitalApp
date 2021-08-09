public class Test {
    public static void main(String[] args) {

        int x = Integer.parseInt("a");
        System.out.println(x);

        /*
        HashMap<Doctor,List<Patient>> hashMap = new HashMap<>();
        Doctor doctor = new Surgeon("baran");
        System.out.println(doctor);
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new EmergencyPatient("selam"));
        hashMap.put(doctor,patientList);
        System.out.println(hashMap.size());
        Hospital hospital = new Hospital(hashMap);
        System.out.println("Enter surgeon name: ");
        Doctor doctor1 = Input.getValidSurgeon(hospital);
        System.out.println(doctor1);
        */
        /*
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = null;     //to keep compiler happy
        boolean isNotValid = true;
        System.out.println("Enter the date.");
        while (isNotValid) {
            String input = scanner.nextLine();
            System.out.println("that is you entered: "+input);
            try {
                localDate = LocalDate.parse(input,formatter);
                isNotValid = false;
            }
            catch (DateTimeParseException e) {
                System.out.println("It is invalid date.Please try again.");
            }
        }
        System.out.println("valid date: "+localDate.toString());
        */

        /*
        String [] arr;
        Scanner scanner = new Scanner(System.in);
        boolean isNotValid = true;
        while(isNotValid) {
            try {
                System.out.println("Enter medicines with comma separated. (Nurofen,Minoset,.....");
                String input = scanner.nextLine();
                arr = input.split(",");
                isNotValid = false;
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");
                scanner.next();        //discard the invalid input
            }
        }

    */
    }
}
