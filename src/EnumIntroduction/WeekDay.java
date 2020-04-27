package EnumIntroduction;

public enum WeekDay {
    SUN, MON, TUE, WED, THU, FRI, SUT;

    public static int between(WeekDay dayFrom, WeekDay dayTo) {
        int indexFrom = dayFrom.ordinal();
        int indexTo = dayTo.ordinal();
        indexTo = indexTo < indexFrom ? indexTo + 7 : indexTo;
        return indexTo - indexFrom;
    }
}
