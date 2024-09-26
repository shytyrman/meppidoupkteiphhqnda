package com.example.meppidoupkteiphhqnda.model.request.PersonMongo;

import java.time.LocalDate;

public record UpdatePersonMongoByDatas(
        String changeFullName,
        LocalDate changeBirthday,
        String changePhoneNumber,
        String changePhoneNumberAdditional,
        String searchId,
        String searchPhoneNumber
) {
}
