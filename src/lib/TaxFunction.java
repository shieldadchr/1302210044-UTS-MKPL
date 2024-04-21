package lib;

public class TaxFunction {
    public static class TaxCalculationInfo {
        private int monthlySalary;
        private int otherMonthlyIncome;
        private int numberOfMonthWorking;
        private int deductible;
        private boolean isMarried;
        private int numberOfChildren;

        public TaxCalculationInfo(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
            this.monthlySalary = monthlySalary;
            this.otherMonthlyIncome = otherMonthlyIncome;
            this.numberOfMonthWorking = numberOfMonthWorking;
            this.deductible = deductible;
            this.isMarried = isMarried;
            this.numberOfChildren = numberOfChildren;
        }

        public int getMonthlySalary() {
            return monthlySalary;
        }

        public int getOtherMonthlyIncome() {
            return otherMonthlyIncome;
        }

        public int getNumberOfMonthWorking() {
            return numberOfMonthWorking;
        }

        public int getDeductible() {
            return deductible;
        }

        public boolean isMarried() {
            return isMarried;
        }

        public int getNumberOfChildren() {
            return numberOfChildren;
        }
    }

    public static final int MAX_WORKING_MONTHS_PER_YEAR = 12;
    public static final int MAX_CHILDREN_FOR_TAX = 3;
    public static final int STANDARD_DEDUCTIBLE = 54000000;
    public static final int PER_CHILD_DEDUCTIBLE = 1500000;

    public static int calculateTax(TaxCalculationInfo taxInfo) {
        validateTaxInfo(taxInfo);

        int monthlySalary = taxInfo.getMonthlySalary();
        int otherMonthlyIncome = taxInfo.getOtherMonthlyIncome();
        int numberOfMonthWorking = taxInfo.getNumberOfMonthWorking();
        int deductible = taxInfo.getDeductible();
        boolean isMarried = taxInfo.isMarried();
        int numberOfChildren = Math.min(taxInfo.getNumberOfChildren(), MAX_CHILDREN_FOR_TAX);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int totalDeductible = deductible + (isMarried ? 0 : STANDARD_DEDUCTIBLE) + Math.min(numberOfChildren * PER_CHILD_DEDUCTIBLE, MAX_CHILDREN_FOR_TAX * PER_CHILD_DEDUCTIBLE);
        int taxableIncome = Math.max(0, totalIncome - totalDeductible);
        int tax = (int) Math.round(0.05 * taxableIncome);
        
        return tax;
    }

    private static void validateTaxInfo(TaxCalculationInfo taxInfo) {
        if (taxInfo.getNumberOfMonthWorking() > MAX_WORKING_MONTHS_PER_YEAR) {
            throw new IllegalArgumentException("Lebih dari 12 bulan bekerja dalam setahun");
        }
    }
}
