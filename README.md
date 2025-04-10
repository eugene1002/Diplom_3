# 🧪 Stellar Burgers — UI & API Тесты

Автоматизированные тесты для веб-приложения [Stellar Burgers](https://stellarburgers.nomoreparties.site/), написанные с использованием:

- 🧷 **Selenide** для UI-тестов  
- 🔗 **Rest Assured** для API  
- 📊 **Allure** для отчётности  
- ⚙️ **JUnit 4**, **Maven**, **Faker**, **Lombok**

---

## 📁 Структура проекта

```
src
├── main
│   ├── java
│   │   ├── locators/                # Локаторы (XPath)
│   │   ├── models/                  # DTO-модели для API
│   │   ├── pages/                   # Page Object страницы
│   │   ├── controllers/             # API контроллеры
│   │   ├── steps/                   # API шаги
│   │   ├── assertions/              # API проверки
│   │   └── utils/                   # Конфиги, браузер, утилиты
├── test
│   └── java
│       ├── ui/                      # UI тесты (JUnit)
│       └── api/                     # API тесты (JUnit)
resources
├── config.properties                # Конфигурация запуска
```

---

## ⚙️ Используемые технологии

| Технология         | Назначение                   |
|--------------------|------------------------------|
| **Selenide**       | Тестирование UI              |
| **Rest Assured**   | Тестирование API             |
| **JUnit 4**        | Тестовый фреймворк           |
| **Allure**         | Отчёты и шаги                |
| **Maven**          | Сборка и зависимости         |
| **DataFaker**      | Генерация случайных данных   |
| **Lombok**         | Упрощение моделей            |

---

## 🔧 Конфигурация (`config.properties`)

```properties
browser=chrome              # chrome / firefox / yandex
isHeadless=false            # true / false
baseUrl=https://stellarburgers.nomoreparties.site/
defaultWait=3
```
> 💡  По умолчанию тесты запускаются в режиме **`headless`** и в браузере **Google Chrome** последней версии

> 💡 Поддерживаются все популярные браузеры, включая **Yandex**. Для него используется собственный `WebDriverProvider` и `chromedriver` из `resources`. 

> 💡 При использовании **browser=yandex**, запускается кастомный **YandexWebDriverProvider.java**, который
использует установленный в системе Яндекс Браузер
применяет chromedriver из расположения`src/test/resources/drivers/yandexdriver-132`
---

## 🚀 Запуск тестов

### 📦 Полный запуск всех тестов

```bash
mvn clean test
```

### 🧪 Запуск с параметрами (переопределить config.properties):

```bash
mvn clean test -Dbrowser=chrome -DisHeadless=true -DbaseUrl=https://stellarburgers.nomoreparties.site/
```

### 📄 Запуск одного теста:

```bash
mvn test -Dtest=LoginTest
```

---

## 📊 Allure отчёт

1. Запустить тесты
2. Затем:

```bash
allure serve target/allure-results
```

> Откроется браузер с красивым Allure-отчётом: шаги, логи, вложения и т.д.

---

## ✅ Покрытие

- Регистрация нового пользователя
- Вход разными способами (личный кабинет, форма, восстановление)
- Выход из аккаунта
- Получение и создание заказов
- Проверка ингредиентов и структуры ответа
- UI + API интеграция

---

## 📍 Требования

- Java 11+
- Maven 3.6+
- Allure CLI (`brew install allure` или [инструкция](https://docs.qameta.io/allure/#_installing_a_commandline))

---

## 💬 Контакты

Автор: **Мандриков Е.А.**  
Платформа: [Yandex.Practicum](https://practicum.yandex.ru/)  
📬 Telegram: [@eugene1002](https://t.me/eugene1002)  
🌐 GitHub: [github.com/mandrikov-ea](https://github.com/mandrikov-ea)