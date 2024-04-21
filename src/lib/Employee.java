package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;
    private boolean isForeigner;
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private List<String> childNames;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;

        this.childNames = new LinkedList<>();
    }

    public void setMonthlySalary(Grade grade) {
        switch (grade) {
            case GRADE_1:
                monthlySalary = Constants.GRADE_1_SALARY;
                break;
            case GRADE_2:
                monthlySalary = Constants.GRADE_2_SALARY;
                break;
            case GRADE_3:
                monthlySalary = Constants.GRADE_3_SALARY;
                break;
        }

        if (isForeigner) {
            monthlySalary *= Constants.FOREIGNER_MULTIPLIER;
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName) {
        this.spouseName = spouseName;
    }

    public void addChild(String childName) {
        this.childNames.add(childName);
    }

    public int getAnnualIncomeTax() {
        int monthWorkingInYear = calculateMonthsWorkedInYear();

        TaxFunction.TaxCalculationInfo taxInfo = new TaxFunction.TaxCalculationInfo(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseName != null, childNames.size());

        return TaxFunction.calculateTax(taxInfo);
    }

    private int calculateMonthsWorkedInYear() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        int monthWorkingInYear;
        if (currentYear == dateJoined.getYear()) {
            monthWorkingInYear = currentMonth - dateJoined.getMonthValue();
        } else {
            monthWorkingInYear = 12;
        }
        return monthWorkingInYear;
    }

    private static class Constants {
        static final int GRADE_1_SALARY = 3000000;
        static final int GRADE_2_SALARY = 5000000;
        static final int GRADE_3_SALARY = 7000000;
        static final double FOREIGNER_MULTIPLIER = 1.5;
    }

    public enum Grade {
        GRADE_1,
        GRADE_2,
        GRADE_3
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
