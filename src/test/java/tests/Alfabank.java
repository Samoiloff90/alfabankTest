package tests;

import helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class Alfabank {
    @Test
    @DisplayName("Переход на страницу Интернет-банка")
    void checkOpenPageInternetBankTest() {
        step("Открытие страницы Альфа-банка", () ->
                open("https://alfabank.ru/"));

        step("Клик по кнопке Интернет-банк", () -> {
            $(".H1Cda9").click();
        });

        step("title главной страницы должен быть: Интернет-банк «Альфа-Клик»", () -> {
            String expectedTitle = "Интернет-банк «Альфа-Клик»";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Проверка заголовка блока обратной связи")
    void titleFeedback() {
        step("Открытие страницы Альфа-банка", () ->
                open("https://alfabank.ru/"));

        step("Находим заголовок", () ->
                $x("//p[text()='Ваши персональные предложения готовы']").shouldHave(text("Ваши персональные предложения готовы")));
    }

    @Test
    @DisplayName("Проверка алертов в форме обратной связи")
    void emptyPhoneAlert() {
        step("Ввести номер из 9 цифр", () -> {
            $("[data-test-id='phoneInput']").scrollTo().setValue("111111111");
        });

        step("Кликнуть на кнопку 'Узнать'", () -> {
            $x("//span[text()='Узнать']").click();
        });

        step("Проверка алерта телефона", () -> {
            $("[data-test-id='captionError-phoneNumber']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67"));
        });

        step("Проверка алерта о согласии на обработку перс.данных", () -> {
            $x("//p[text()='Чтобы продолжить, необходимо ваше согласие']").shouldHave(text("Чтобы продолжить, необходимо ваше согласие"));
        });
    }


    @Test
    @DisplayName("Проверка наличия заголовка на странице")
    void titleTest() {
        step("Страница должна содержать текст 'Альфа-Банк - кредитные и дебетовые карты, кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк'", () -> {
            String expectedTitle = "Альфа-Банк - кредитные и дебетовые карты, кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Переход на страницу Альфа-Вклад")
    void checkOpenPageDepositsTest() {
        step("Клик по кнопке Альфа-Вклад", () -> {
            $("#Main-Page-Important-Info-3").click();
        });

        step("title главной страницы должен быть: Вклад «Альфа-Вклад» | " +
                "Открыть выгодный вклад в Альфа-Банке", () -> {
            String expectedTitle = "Вклад «Альфа-Вклад» | Открыть выгодный вклад в Альфа-Банке";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Переход на страницу Накопительный счёт")
    void checkOpenPageSavingsAccountTest() {
        step("Клик по кнопке Альфа-Счёт", () -> {
            $("#Main-Page-Important-Info-2").click();
        });

        step("title главной страницы должен быть: Накопительный счет «Альфа-Счет» - " +
                "вклады и инвестиции в Альфа-Банк", () -> {
            String expectedTitle = "Накопительный счет «Альфа-Счет» - вклады и инвестиции в Альфа-Банк";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Проверка ошибок в Console log")
    void consoleShouldNotHaveErrorsTest() {
        step("Открытие страницы Альфа-банка", () ->
                open("https://alfabank.ru/"));

        step("Проверяем, что консоль не содержит  ошибок 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
