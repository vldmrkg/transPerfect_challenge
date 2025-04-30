# 🧪 Selenium QA Automation Project

## 🎯 Test Objectives
The objective of this test plan is to validate the key functionalities of a demo web application through automated tests written in Java using Selenium WebDriver. These tests aim to ensure that the user interface behaves as expected, handles user interactions correctly, and appropriately manages edge cases.

## 📦 Test Scope
This test plan covers the following areas:

- ✅ Dialog Functionality
- ✅ Window Handling
- ✅ Employee Management
- ✅ Autocomplete Input
- ✅ Multi-select Dropdown

## 🧪 High-Level Test Cases

### Task 1: Dialog Test
1. Open the page.
2. Click the "Open Dialog" button.
3. Verify that the dialog and its buttons are visible.
4. Check the background color of the "Yes" button.
5. Focus on the "X" close icon.
6. Press Enter to close the dialog.
7. Verify the dialog is successfully closed.

### Task 2: Window Test
1. Open the page.
2. Click the "Open Window" button.
3. Verify data inside the new window (name, buttons, text).
4. Maximize the window.
5. Verify the maximize button is no longer visible.
6. Close the window.
7. Verify it is closed.

### Task 3: Employees Test
1. Open the page.
2. Filter employees based in the USA.
3. Print each employee's name, job title, phone number, and address.
4. Export online employees from the USA to an Excel file.
5. Validate the content of the exported file.

### Task 4: Autocomplete Sport Test
1. Open the page.
2. Select a favorite sport from the autocomplete input.
3. Verify the selected result is displayed.
4. Clear the selection using the "X" icon.
5. Verify the selection has been cleared.

### Task 5: MultiSelect Sport Test
1. Open the page.
2. Open the multiselect dropdown and verify all available options.
3. Select "Tennis" and "Football".
4. Verify that the selected sports are displayed correctly.

## ✅ Project Architecture
- Page Object Model (POM) for test structure.
- JUnit 5 for test execution.
- Apache POI for Excel handling.
- JSON for test data.
- All tests run in Google Chrome via ChromeDriver.

## 📁 Test Data
- Stored in the `resources` folder.
- Read using a `TestDataReader` utility class.

## ❌ Out of Scope
- Performance or load testing.
- Backend or API testing.

## 📌 Assumptions
- All test cases are executed in **Google Chrome browser** using the latest stable version.
- The **test environment** is local unless otherwise specified.
- The **web application** under test is assumed to be fully functional and accessible during testing.
- **Employee data** is considered static and does not change dynamically.

## 🚀 Running the Tests

### Prerequisites
Make sure **ChromeDriver** is installed and available in your system `PATH`.
- Alternatively, set the driver path in your test setup code:

```java
//System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
```


## 🌐 Project Repository
GitHub: [https://github.com/vldmrkg/transPerfect_challenge](https://github.com/vldmrkg/transPerfect_challenge)
