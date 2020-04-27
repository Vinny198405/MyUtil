package EnumIntroduction;

public class EnumTestAppl {
    public static void main(String[] args) {
//		WeekDay wd = WeekDay.SUN;
//
//		String str = "FRI";
//		wd  = WeekDay.valueOf(str);
//		for(WeekDay wd: WeekDay.values()) {
//			weekDayComment(wd);
//		}
//	printWeightUnitConversion(1000, WeightUnit.KG, WeightUnit.MGr);
        System.out.printf("number days from %s to %s is %d\n",
                WeekDay.FRI, WeekDay.SUN,
                WeekDay.between(WeekDay.FRI, WeekDay.SUN));

    }
    public static void weekDayComment(WeekDay wd) {
        switch (wd) {
            case SUN: System.out.println(wd + " Weekend if you are not in Israel"); return;
            case FRI: System.out.println(wd + " Weekend if you are in Israel"); return;
            case SUT: System.out.println(wd + " Weekend in all countries");return;
            default: System.out.println(wd + " Regular day");
        }
    }
    static public void printWeightUnitConversion
            (float amount, WeightUnit src, WeightUnit dst) {
        System.out.printf("%f %s equals %f %s \n",
                amount, src, amount * src.convert(dst), dst);
    }
}
