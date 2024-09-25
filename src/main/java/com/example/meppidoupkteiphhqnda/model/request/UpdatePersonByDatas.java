package com.example.meppidoupkteiphhqnda.model.request;

import java.time.LocalDate;

public record UpdatePersonByDatas(
        String changeFullName,
        LocalDate changeBirthday,
        String changePhoneNumber,
        String changePhoneNumberAdditional,
        Long searchId,
        String searchPhoneNumber
) {
}
