# 🛡️ PhishGuard: AI-Based Phishing URL Detection System

PhishGuard is a web-based application designed to identify potentially malicious URLs using machine learning. It analyzes different characteristics of a URL and predicts whether it is **legitimate** or **potentially phishing**.

The project aims to demonstrate how machine learning can help improve awareness of phishing attacks and contribute to safer internet browsing.

## 🎯 Objectives

- Detect potentially fraudulent or phishing URLs.
- Extract useful features from URLs for machine learning analysis.
- Train a Random Forest classification model using URL-based features.
- Provide a simple web interface for users to check URLs.
- Demonstrate the integration of machine learning with a Java-based web application.

## ⚙️ Technologies Used

- **Java 21** — Core programming language
- **Spring Boot** — Web application framework
- **Maven** — Dependency management and project build
- **Weka** — Machine learning and model training
- **Apache Commons CSV** — CSV dataset processing
- **Random Forest** — Machine learning classification algorithm
- **HTML & CSS** — Web interface

## 🚀 Features

- User-friendly web interface for entering URLs.
- Automatic extraction of URL-based features.
- Machine learning-based URL classification.
- Separate classification of legitimate and potentially phishing URLs.
- Dataset processing and model training using Weka.

## 🧠 How It Works

1. The user enters a URL into the PhishGuard web interface.
2. The application extracts relevant features from the URL, such as its length, number of dots, hyphens, subdomains, and suspicious words.
3. The extracted features are passed to a trained Random Forest model.
4. The model predicts whether the URL is legitimate or potentially phishing.
5. The application displays the prediction to the user.

## 📂 Project Structure

```text
PhishGuard/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/phishingdetector/phishingdetector/
│       │       ├── PhishingDetectorApplication.java
│       │       ├── HomeController.java
│       │       ├── ScanController.java
│       │       ├── UrlFeatureExtractor.java
│       │       ├── UrlDatasetGenerator.java
│       │       ├── UrlOnlyDatasetConverter.java
│       │       └── UrlOnlyModelTrainer.java
│       └── resources/
│           └── data/
│               ├── urlset_final.csv
│               ├── url_only_dataset.csv
│               ├── url_only_dataset.arff
│               └── url_only_model.model
├── pom.xml
└── README.md
```

*Note: The model file and dataset files will be available after the corresponding processing and training steps have completed successfully. Other project files may also be present.*

## 💻 Requirements

Before running the project, ensure you have installed:

- Java Development Kit (JDK) 21
- IntelliJ IDEA or another Java IDE
- Maven
- Git (optional, for version control)

## ▶️ How to Run

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

Replace `YOUR_GITHUB_REPOSITORY_URL` with your actual GitHub repository URL.

### 2. Open the Project

Open the cloned project in IntelliJ IDEA and allow Maven to load the dependencies.

### 3. Run the Application

Locate `PhishingDetectorApplication.java` and run its `main()` method.

### 4. Open the Web Application

Open your browser and visit:

```text
http://localhost:8080
```

Enter a URL into the interface to test the application's URL-scanning functionality.

**Note:** URL prediction requires the trained model to be successfully generated and integrated with the web application.

## 📊 Dataset and Model

The project uses a dataset containing URL information and labels for legitimate and phishing samples. The data is processed to generate numerical URL features suitable for machine learning.

The URL-only dataset contains 16 extracted features and a classification label. These features are used to train a Random Forest model with the Weka machine learning library.

Model performance will be reported after the URL-only model has been successfully evaluated. Results from a different model or feature set should not be treated as the performance of this model.

## ⚠️ Limitations

- The model's predictions are estimates and may contain false positives or false negatives.
- Some phishing URLs may not exhibit obvious suspicious characteristics.
- URL-based analysis alone cannot reliably identify every phishing attack.
- The model's performance depends on the quality and representativeness of its training data.
- A URL classified as legitimate is not guaranteed to be safe.

**PhishGuard is an educational project and should not be considered a replacement for professional security tools.**

## 🔮 Future Improvements

- Improve model performance through additional data and feature engineering.
- Evaluate the model on unseen URLs and datasets.
- Add URL reputation checks using trusted threat-intelligence sources.
- Improve the web interface and display prediction confidence where appropriate.
- Add more comprehensive testing and security validation.
- Deploy the application to a suitable hosting platform.

## 🎓 Project Information

**Project Name:** PhishGuard  
**Project Type:** Academic Machine Learning and Web Development Project  
**Degree:** B.Sc. Engineering in Information and Communication Technology (ICT)  
**University:** Mawlana Bhashani Science and Technology University (MBSTU)

## 👨‍💻 Author

**Protham Sarker**

## 📄 License

This project was developed for educational and academic purposes. Add an appropriate open-source license if you intend to distribute or reuse the project publicly.
