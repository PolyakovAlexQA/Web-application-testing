package ru.netology.card;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTest {
    @Test
    void OpenHappyPath() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Поляков Александр");
        form.$("[data-test-id=phone] input").setValue("+79999000203");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }


    @Test
    void  ValidationCheckNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Polyakov Alexandr");
        form.$("[data-test-id=phone] input").setValue("+79999000203");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".input_invalid[data-test-id=name]").shouldHave(exactText("Фамилия и имя Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void EmptyNumberTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Поляков Александр");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".input_invalid[data-test-id=phone]").shouldHave(exactText("Мобильный телефон Поле обязательно для заполнения"));
    }

    @Test
    void ValidationCheckPhoneTest(){
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Поляков Александр");
        form.$("[data-test-id=agreement]").click();
        form.$("[data-test-id=phone] input").setValue("79999000203");
        form.$("[role=button]").click();
        $(".input_invalid[data-test-id=phone]").shouldHave(exactText("Мобильный телефон Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void AgreementCheckTest(){
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Поляков Александр");
        form.$("[data-test-id=phone] input").setValue("+79999000203");
        form.$("[role=button]").click();
        $(".input_invalid[data-test-id=agreement]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

