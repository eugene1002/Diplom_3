# 🧪 Stellar Burgers Tests

![GitHub Repo size](https://img.shields.io/github/repo-size/eugene1002/yandex_burger)
![GitHub last commit](https://img.shields.io/github/last-commit/eugene1002/yandex_burger)
![GitHub branch](https://img.shields.io/badge/branch-master-blue)

Автоматизированные UI + API тесты для [Stellar Burgers](https://stellarburgers.nomoreparties.site/)

---

## 🔧 Стек технологий

- 🧷 **Selenide** — UI тестирование
- 🔗 **Rest Assured** — API тестирование
- 📊 **Allure** — отчёты, шаги и вложения
- ⚙️ **JUnit 4**, **Maven**, **Lombok**, **Faker**

---

## 🚀 Запуск

```bash
mvn clean test
```

> Используется конфигурация из `src/test/resources/config.properties`

Переопределение параметров:

```bash
mvn clean test -Dbrowser=chrome -DisHeadless=false -DbaseUrl=https://stellarburgers.nomoreparties.site/
```

---

## 📊 Отчёт Allure

```bash
allure serve target/allure-results
```

---

## ✅ Покрытие

- [x] Авторизация разными способами
- [x] Регистрация нового пользователя
- [x] Выход из аккаунта
- [x] Проверка конструктора бургеров
- [x] UI + API интеграция
- [x] Скриншоты при ошибках
- [x] Поддержка Chrome, Firefox и Yandex

---

## ⚙️ Конфигурация (config.properties)

```properties
browser=chrome               # chrome / firefox / yandex
isHeadless=false             # true / false
baseUrl=https://stellarburgers.nomoreparties.site/
defaultWait=3                # seconds
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
    - драйвер `chromedriver-132` из `src/test/resources/drivers/`

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