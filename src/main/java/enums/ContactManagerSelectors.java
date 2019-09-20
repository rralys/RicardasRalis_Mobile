package enums;

public enum ContactManagerSelectors {

    ADD_BUTTON("com.example.android.contactmanager:id/addContactButton"),
    ADD_CONTACT_TITLE("android:id/title"),
    TARGET_ACCOUNT_FIELD("com.example.android.contactmanager:id/accountSpinner"),
    CONTACT_NAME_FIELD("com.example.android.contactmanager:id/contactNameEditText"),
    CONTACT_PHONE_FIELD("com.example.android.contactmanager:id/contactPhoneEditText"),
    KEYBOARD_XPATH("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout");

    final String selector;

    ContactManagerSelectors(String text) {
        selector = text;
    }

    public String getSelector() {
        return selector;
    }
}
