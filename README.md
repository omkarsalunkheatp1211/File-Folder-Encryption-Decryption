# File Encryption and Decryption Tool

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Usage](#usage)
- [Security Measures](#security-measures)

## Features
- AES encryption for securing files and folders
- Decryption functionality with password protection
- File compression and extraction support
- GUI-based file selection and notification
- Password lockout mechanism to prevent brute-force attacks
- Cross-platform support

## Technologies Used
- Java
- AES Encryption
- Java Swing (GUI)
- File Handling
- Zip Compression/Extraction
- Secure Hash Algorithm (SHA-256) for password hashing

## Setup
Clone the repository to your local machine:
```sh
git clone https://github.com/
```

Navigate to the project directory:
```sh
cd <project-directory>
```

Compile all files:
```sh
javac *.java
```

Run the main file:
```sh
java Main
```

## Usage
1. Open the application.
2. Select the file or folder you want to encrypt.
3. Enter a strong password for encryption.
4. Click 'Encrypt' to secure the file.
5. To decrypt, select the encrypted file and enter the correct password.
6. Click 'Decrypt' to retrieve the original file.

## Security Measures
- Uses AES-256 encryption for strong security.
- Passwords are hashed using SHA-256 before storage.
- Implements a password lockout mechanism after multiple failed attempts.
- Securely deletes temporary files after operations.

