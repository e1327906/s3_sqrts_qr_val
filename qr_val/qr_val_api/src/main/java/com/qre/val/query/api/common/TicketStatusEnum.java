package com.qre.val.query.api.common;

public enum TicketStatusEnum {

    ACTIVE(1),
    ENTRY(2),
    EXIT(3),
    ENTRY_UPGRADE(4),
    EXIT_UPGRADE(5),
    CANCELED(6),
    REFUNDED(7),
    INACTIVE(0);

    private final int value;

    TicketStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Optional: You can also add a method to get the enum value from the integer value
    public static TicketStatusEnum fromValue(int value) {
        for (TicketStatusEnum type : TicketStatusEnum.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TicketStatusEnum value: " + value);
    }
}
