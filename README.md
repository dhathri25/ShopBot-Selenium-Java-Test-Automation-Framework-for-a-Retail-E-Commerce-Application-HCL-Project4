# 🛒 ShopBot – Selenium-Java Test Automation Framework

A **Page Object Model (POM)** based Selenium-Java automation framework for the retail e-commerce demo site [saucedemo.com](https://www.saucedemo.com), built with TestNG, WebDriverManager, and ExtentReports.

---

## 📁 Project Structure

```
shopbot-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java          # Shared WebDriver + WebDriverWait setup
│   │   │   │   └── DriverFactory.java     # Browser init from config
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java      # Reads config.properties
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java         # Login page actions & locators
│   │   │   │   ├── ProductPage.java       # Product listing, sorting, logout
│   │   │   │   ├── CartPage.java          # Cart add/remove/count
│   │   │   │   └── CheckoutPage.java      # Checkout flow actions
│   │   │   └── utils/
│   │   │       └── ExtentManager.java     # ExtentReports singleton
│   │   └── resources/
│   │       └── config.properties          # browser, baseUrl, timeout
│   └── test/
│       └── java/
│           ├── listeners/
│           │   └── TestListener.java      # ITestListener: screenshot + report
│           └── tests/
│               ├── BaseTest.java          # @BeforeMethod / @AfterMethod
│               ├── LoginTest.java         # Auth tests + @DataProvider
│               ├── ProductTest.java       # Product listing & sorting tests
│               ├── CartTest.java          # Shopping cart tests
│               ├── CheckoutTest.java      # Checkout flow tests
│               ├── LogoutTest.java        # Logout tests
│               └── ProblemUserTest.java   # Problem user behavior tests
├── reports/
│   └── ExtentReport.html                  # Auto-generated HTML report
├── screenshots/                           # Auto-saved failure screenshots
├── testng.xml                             # Suite configuration
└── pom.xml                                # Maven dependencies
```

---

## ⚙️ Configuration

All configurable values are in `src/main/resources/config.properties`:

```properties
browser=chrome
baseUrl=https://www.saucedemo.com
timeout=20
```

| Property  | Description                        | Default                          |
|-----------|------------------------------------|----------------------------------|
| `browser` | Browser to use (`chrome`/`firefox`)| `chrome`                         |
| `baseUrl` | Application under test URL         | `https://www.saucedemo.com`      |
| `timeout` | Explicit wait timeout in seconds   | `20`                             |

> ⚠️ Never hardcode URLs or credentials in test or page classes.

---

## 🧪 Test Users

All users share the password: `secret_sauce`

| Username          | Behaviour                              |
|-------------------|----------------------------------------|
| `standard_user`   | Normal login and full flow             |
| `locked_out_user` | Login blocked with error message       |
| `problem_user`    | Intentional UI bugs (broken images)    |

---

## 🚀 How to Run

### Prerequisites

- Java 11+
- Maven 3.6+
- Google Chrome or Firefox installed

### Run all tests

```bash
mvn test
```

The suite runs using `testng.xml` automatically via Maven Surefire.

### Run a specific test class

```bash
mvn test -Dtest=LoginTest
```

### Switch browser (without editing config)

```bash
mvn test -Dbrowser=firefox
```

---

## 📋 Test Modules

### Module 1 – User Authentication (`LoginTest`, `LogoutTest`)
- ✅ Successful login with `standard_user`
- ✅ Locked user sees error message
- ✅ Empty credentials show validation error
- ✅ Logout redirects to login page

### Module 2 – Product Listing & Sorting (`ProductTest`)
- ✅ Product listing page shows at least one product
- ✅ Sort by Name A→Z, verify first item
- ✅ Sort by Price Low→High, verify cheapest item
- ✅ Open product detail and verify name/price

### Module 3 – Shopping Cart (`CartTest`)
- ✅ Add one product → cart badge = 1
- ✅ Add two products → cart badge = 2
- ✅ Remove item from cart page
- ✅ Cart retains items after navigating back

### Module 4 – Checkout Flow (`CheckoutTest`)
- ✅ Fill customer details and proceed to order summary
- ✅ Verify product name and total price on summary page
- ✅ Complete order and verify confirmation message

### Module 5 – Problem User (`ProblemUserTest`)
- ✅ Login as `problem_user` and verify page loads
- ✅ Attempt to add product and document behavior
- ✅ Verify page source for broken UI indicators

---

## 🏗️ Framework Design

### Page Object Model (POM)
- Each page has a **dedicated Page class** containing all locators and action methods.
- Test classes **only call Page class methods** — no `findElement` or `click` in tests.
- `BasePage` holds shared `WebDriver` and `WebDriverWait` instances.

### Wait Strategy
- **No `Thread.sleep()` anywhere** — all waits use `WebDriverWait` with `ExpectedConditions`.

### Driver Management
- `DriverFactory` initialises the browser using **WebDriverManager** (no manual driver setup).
- Browser type is read from `config.properties`.

### Data-Driven Testing
- `LoginTest` uses `@DataProvider` to run login scenarios across `standard_user`, `locked_out_user`, and empty credentials.

### Listeners & Reporting
- `TestListener` implements `ITestListener`:
  - Captures a **timestamped screenshot** on every test failure → saved in `/screenshots/`
  - Logs pass/fail status and attaches screenshots to **ExtentReports**
- HTML report generated at `reports/ExtentReport.html` after every run.

---

## 📦 Maven Dependencies

| Dependency         | Version   | Purpose                          |
|--------------------|-----------|----------------------------------|
| `selenium-java`    | 4.21.0    | Browser automation               |
| `testng`           | 7.9.0     | Test framework & annotations     |
| `webdrivermanager` | 5.8.0     | Automatic driver management      |
| `extentreports`    | 5.1.1     | HTML test reporting              |

---

## 📊 Reports & Evidence

After a test run:

| Output | Location |
|--------|----------|
| ExtentReport (HTML) | `reports/ExtentReport.html` |
| TestNG default report | `test-output/index.html` |
| Failure screenshots | `screenshots/<testName>_<timestamp>.png` |

---

## 📌 Business Rules Followed

| Rule | Status |
|------|--------|
| No `Thread.sleep()` | ✅ Enforced |
| No hardcoded URLs or credentials | ✅ All in `config.properties` |
| No WebDriver code in test methods | ✅ Handled via `BaseTest` / `DriverFactory` |
| Suite runs via `mvn test` | ✅ Configured in `pom.xml` |

---

## 👥 Team Contributions

| Member | Contribution |
|--------|-------------|
| Member 1 | `LoginPage.java`, `LoginTest.java` |
| Member 2 | `ProductPage.java`, `ProductTest.java` |
| Member 3 | `CartPage.java`, `CartTest.java` |
| Member 4 | `CheckoutPage.java`, `CheckoutTest.java` |
| Member 5 | `BasePage`, `DriverFactory`, `ConfigReader`, `TestListener`, `LogoutTest`, `ProblemUserTest` |

> Update this table with actual team member names before submission.

---

## 🔮 Good-to-Have (Future Enhancements)

- [ ] `@DataProvider` reading from JSON/Excel file
- [ ] Parallel execution in `testng.xml` (`thread-count="2"`)
- [ ] `FluentWait` for slow-loading elements
- [ ] Headless browser mode via `config.properties`
- [ ] Retry on failure using `IRetryAnalyzer`
