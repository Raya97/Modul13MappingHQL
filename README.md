## UK
# Modul13MappingHQL

## Огляд

Цей проект є Java-додатком, який демонструє використання Hibernate ORM (Object-Relational Mapping) і HQL (Hibernate Query Language) для управління та маніпулювання даними в реляційній базі даних. Проект є частиною навчального модуля, зосередженого на просунутих взаємодіях з базами даних за допомогою Hibernate, включаючи CRUD (створення, читання, оновлення, видалення) операції над сутностями, такими як `Client`, `Planet` та `Ticket`.

## Функціональні можливості

- **Управління клієнтами:** Створення, оновлення, видалення та отримання даних клієнтів з бази даних.
- **Управління планетами:** Управління сутностями, що представляють планети, включаючи створення, оновлення, видалення та отримання даних про планети.
- **Управління квитками:** Обробка операцій з квитками, забезпечення того, що кожен квиток асоційований з дійсним клієнтом і існуючими планетами.
- **Обробка помилок:** Надійна обробка помилок з логуванням у файл, що забезпечує запис будь-яких проблем під час транзакцій з базою даних для подальшого налагодження.

## Структура проекту

- **Сутності:**
    - `Client`: Представляє клієнта в системі.
    - `Planet`: Представляє сутність планети.
    - `Ticket`: Представляє сутність квитка, що пов'язує клієнта з деталями подорожі між двома планетами.

- **Сервіси:**
    - `ClientCrudService`: Забезпечує CRUD-операції для сутності `Client`.
    - `PlanetCrudService`: Управляє сутностями `Planet` з операціями створення, оновлення, видалення та отримання.
    - `TicketCrudService`: Обробляє створення та валідацію квитків, забезпечуючи дійсність усіх залучених сутностей.

- **Тести:**
    - Юніт-тести з використанням JUnit і Mockito для забезпечення правильної роботи класів сервісів, включаючи тести для крайніх випадків, таких як null-сутності або неіснуючі записи.


## EN
# Modul13MappingHQL

## Overview

This project is a Java-based application that demonstrates the use of Hibernate ORM (Object-Relational Mapping) and HQL (Hibernate Query Language) for managing and manipulating data in a relational database. The project is part of a learning module focused on advanced database interactions using Hibernate, including CRUD (Create, Read, Update, Delete) operations on entities such as `Client`, `Planet`, and `Ticket`.

## Features

- **Client Management:** Create, update, delete, and retrieve client data from the database.
- **Planet Management:** Manage the entities representing planets, including creating, updating, deleting, and retrieving planet data.
- **Ticket Management:** Handle ticketing operations, ensuring that each ticket is associated with a valid client and existing planets.
- **Error Handling:** Robust error handling with logging to a file, ensuring any issues during database transactions are recorded for later debugging.

## Project Structure

- **Entities:**
  - `Client`: Represents a client in the system.
  - `Planet`: Represents a planet entity.
  - `Ticket`: Represents a ticket entity that connects a client with travel details between two planets.

- **Services:**
  - `ClientCrudService`: Provides CRUD operations for the `Client` entity.
  - `PlanetCrudService`: Manages `Planet` entities with operations such as create, update, delete, and fetch.
  - `TicketCrudService`: Handles `Ticket` creation and validation, ensuring all entities involved are valid.

- **Tests:**
  - Unit tests using JUnit and Mockito to ensure the service classes perform as expected, with tests for edge cases like null entities or non-existent records.


