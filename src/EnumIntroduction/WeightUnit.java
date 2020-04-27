package EnumIntroduction;

public enum WeightUnit {
    GR(1.0f), KG(1000.0f), TN(1000000.0f), LBS(453.592f), MGr(0.01f);
    private float value;
    private WeightUnit(float value) {
        this.value = value;
    }
    public float convert(WeightUnit other) {
        return value / other.value;
    }
}
