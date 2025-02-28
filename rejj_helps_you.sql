CREATE DATABASE rejj_helps_you;
USE rejj_helps_you;

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);


CREATE TABLE invoices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

CREATE TABLE invoice_services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT,
    service_id INT,
    hours INT NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
);

-- ex
INSERT INTO clients (name) VALUES ('Rejj Helps You'), ('Computer Wizards'), ('Yababadobee.co');
INSERT INTO services (name, price) VALUES ('Social Media Manager', 50.00), ('Product Research', 35.00);
