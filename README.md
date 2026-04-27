#  ShopBot – Selenium-Java Test Automation Framework

A **Page Object Model (POM)** based Selenium-Java automation framework for the retail e-commerce demo site [saucedemo.com](https://www.saucedemo.com), built with TestNG, WebDriverManager, and ExtentReports.

---

##  Project Structure

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


## Test Users

All users share the password: `secret_sauce`

| Username          | Behaviour                              |
|-------------------|----------------------------------------|
| `standard_user`   | Normal login and full flow             |
| `locked_out_user` | Login blocked with error message       |
| `problem_user`    | Intentional UI bugs (broken images)    |

---

##  How to Run

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


## Framework Design

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
 
 
Developed By Dhathri Putty For HCL Hackathon


