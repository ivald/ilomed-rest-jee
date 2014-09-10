package constant;

/**
 * Created by ivald79 on 07/09/2014.
 */
public enum ResponseError {
    FAILED(0), SUCCESS(1);

    private int value;

    private ResponseError(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
};
