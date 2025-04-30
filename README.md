# 🧪 Test Plan for Selenium QA Project

## 🎯 Test Objectives

The objective of this test plan is to validate the key functionalities of a demo web application through automated tests written in Java using Selenium WebDriver. These tests aim to ensure that the user interface behaves as expected, handles user interactions correctly, and appropriately manages edge cases.

---

## 📦 Test Scope

This test plan covers the following areas:

- ✅ **Dialog Functionality:** Opening dialogs, verifying content, and closing via keyboard input.
- ✅ **Window Handling:** Opening new browser windows, maximizing, and closing them.
- ✅ **Employee Management:** Filtering employees from the USA, exporting to Excel, and verifying file content.
- ✅ **Autocomplete Input:** Selecting and clearing a favorite sport.
- ✅ **Multi-select Dropdown:** Selecting multiple sports and verifying selected values.

---

## 🧪 High-Level Test Cases

### 1. Dialog Test
- Open the page.
- Click the "Open Dialog" button.
- Verify that the dialog and its buttons are visible.
- Check the background color of the "Yes" button.
- Focus on the "X" close icon.
- Press Enter to close the dialog.
- Verify the dialog is successfully closed.

### 2. Window Test
- Open the page.
- Click the "Open Window" button.
- Verify data inside the new window (name, buttons, text).
- Maximize the window.
- Verify the maximize button is no longer visible.
- Close the window.
- Verify it is closed.

### 3. Employees Test
- Open the page.
- Filter employees based in the USA.
- Print each employee's name, job title, phone number, and address.
- Export online employees from the USA to an Excel file.
- Validate the content of the exported file (`online_employees_usa.xlsx`).

### 4. Autocomplete Sport Test
- Open the page.
- Select a favorite sport from the autocomplete input.
- Verify the selected result is displayed.
- Clear the selection using the "X" icon.
- Verify the selection has been cleared.

### 5. MultiSelect Sport Test
- Open the page.
- Open the multiselect dropdown and verify all available options.
- Select "Tennis" and "Football".
- Verify that the selected sports are displayed correctly.

---

## ✅ Project Architecture

- The project uses the **Page Object Model (POM)** for modular and maintainable test design.
- Test data is stored in **JSON** format for flexibility and external configuration.
- Excel operations (export and validation) are handled via the **Apache POI** library.
- Tests are written with **JUnit 5** and executed in the **Chrome browser** using ChromeDriver.

---

## 📁 Test Data

- Test data files are stored in the `resources` folder.
- Each test reads its required input from a dedicated `.json` file.
- A utility class `TestDataReader` is used to read and parse test data dynamically.

---

## ❌ Out of Scope

- Performance or load testing.
- Backend or API validation.


---

## ✅ Deliverables

- Automated Selenium tests for all 5 functional areas.
- HTML and JUnit test reports.
- Locally generated Excel files for export verification.

---

## 🚀 Running the Tests

### Prerequisites
- Make sure ChromeDriver is installed and available in your system PATH.
- Or set the driver path explicitly:
  ```java
  System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
