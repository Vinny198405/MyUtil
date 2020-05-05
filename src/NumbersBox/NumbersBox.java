package NumbersBox;

public interface NumbersBox extends Iterable<Integer> {
    void addNumber(int number);
    void removeNumber(int number);
    int removeRepeated(); // removing repeated numbers, returns count of removes
    int removeNumbersInRange(int from,int to); // removing numbers in range,returns count of removed numbers

    int size();
}
