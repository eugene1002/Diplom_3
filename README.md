# 🧪 Stellar Burgers — UI & API Тесты

Автоматизированные тесты для веб-приложения [Stellar Burgers](https://stellarburgers.nomoreparties.site/), написанные с использованием:

- 🧷 **Selenide** — для UI-тестов  
- 🔗 **Rest Assured** — для API  
- 📊 **Allure** — для красивых отчётов  
- ⚙️ **JUnit 4**, **Maven**, **Faker**, **Lombok**

---

## 📁 Структура проекта

```
src
├── main
│   ├── java
│   │   ├── locators/                // Локаторы (XPath)
│   │   ├── models/                  // DTO-модели для API
│   │   ├── pages/                   // Page Object страницы
│   │   ├── controllers/             // API контроллеры
│   │   ├── utils/                   // Конфиги, браузер, WebDriverProvider
├── test
│   └── java
│       └── ui/                      // UI тесты (JUnit)
resources
├── config.properties               // Конфигурация запуска
└── drivers/
    └── chromedriver-135            // Драйвер для Yandex Browser
```

---

## ⚙️ Используемые технологии

| Технология         | Назначение                   |
|--------------------|------------------------------|
| **Selenide**       | Тестирование UI              |
| **Rest Assured**   | Тестирование API             |
| **JUnit 4**        | Фреймворк                     |
| **Allure**         | Отчёты и шаги                |
| **Maven**          | Сборка и зависимости         |
| **DataFaker**      | Генерация случайных данных   |
| **Lombok**         | Упрощение моделей            |

---

## 🔧 Конфигурация (`config.properties`)

```properties
browser=chrome               # chrome / firefox / yandex
isHeadless=true              # true / false
baseUrl=https://stellarburgers.nomoreparties.site/
defaultWait=3                # Время ожидания в секундах
```

> 💡 Для **Yandex Browser** используется кастомный WebDriverProvider `YandexWebDriverProvider.java`, который запускает браузер с локальным `chromedriver`.

---

## 🚀 Запуск тестов

### 🔹 Полный запуск всех тестов

```bash
mvn clean test
```

### 🔹 Запуск с параметрами (переопределение `config.properties`)

```bash
mvn clean test -Dbrowser=firefox -DisHeadless=false -DbaseUrl=https://stellarburgers.nomoreparties.site/
```

### 🔹 Запуск одного теста

```bash
mvn test -Dtest=LoginTest
```

---

## 🖥 Особенности запуска

- 🧠 При `isHeadless=false`, окно браузера автоматически **разворачивается на весь экран**
- 🧭 `browser=yandex` использует:
  - бинарник `Yandex`
  - драйвер `chromedriver-135` из `src/test/resources/drivers/`

---

## 📊 Allure отчёт

1. Запусти тесты
2. Сформируй отчёт:

```bash
allure serve target/allure-results
```

---

## ✅ Покрытие

- [x] Регистрация нового пользователя
- [x] Авторизация через разные точки (личный кабинет, формы)
- [x] Выход из аккаунта
- [x] Навигация по разделам конструктора (Булки / Соусы / Начинки)
- [x] Негативный кейс при регистрации
- [x] Интеграция UI + API

---

## 🧠 Возможности для расширения

- 🔐 Тесты восстановления пароля  
- 📩 Интеграция с Email / Telegram  
- 🧱 Разделение `BaseApiTest` и `BaseUiTest`  
- ⚙️ CI (GitHub Actions, GitLab CI)  
- 🧪 Параллельный запуск тестов

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