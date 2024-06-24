
CREATE DATABASE FlowerShop;

USE FlowerShop;

CREATE TABLE User(
                     U_id varchar(10)primary key,
                     U_name varchar(50),
                     U_passwrod varchar(50)
);

CREATE TABLE Employee(
                         E_id varchar(10) primary key,
                         U_id varchar(10),
                         E_name varchar(50),
                         Address varchar(100),
                         Email varchar(50),
                         JobRole varchar(50),
                         TelNo varchar(12),
                         FOREIGN KEY (U_id) REFERENCES User(U_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Customer(
                         C_id varchar(10) primary key,
                         U_id varchar(10),
                         C_name varchar(50),
                         Address varchar(100),
                         TelNo varchar(12),
                         FOREIGN KEY (U_id) REFERENCES User(U_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Event(
                      E_code varchar(10) primary key,
                      E_name varchar(50),
                      Date DATE,
                      Price double not null
);

CREATE TABLE E_order(
                        EO_id varchar(10)primary key,
                        C_id varchar(10),
                        E_code varchar(10),
                        Amount double not null,
                        FOREIGN KEY (C_id) REFERENCES Customer(C_id)ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (E_code) REFERENCES Event(E_code)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE EventDetail(
                            ED_code varchar(10)primary key,
                            E_id varchar(10),
                            E_code varchar(10),
                            FOREIGN KEY (E_id) REFERENCES Employee(E_id)ON UPDATE CASCADE ON DELETE CASCADE,
                            FOREIGN KEY (E_code) REFERENCES Event(E_code)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Product(
                        P_code varchar(10)primary key,
                        P_name varchar(50),
                        Price double not null
);


CREATE TABLE P_order(
                        PO_id varchar(10)primary key,
                        C_id varchar(10),
                        FOREIGN KEY (C_id) REFERENCES Customer(C_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DesignDetail(
                             D_id varchar(10)primary key,
                             E_id varchar(10),
                             p_code varchar(10),
                             DesignerName varchar(50),
                             Description varchar(100),
                             FOREIGN KEY (E_id) REFERENCES Employee(E_id)ON UPDATE CASCADE ON DELETE CASCADE,
                             FOREIGN KEY (P_code) REFERENCES Product(P_code)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Flower(
                       F_code varchar(10)primary key,
                       F_name varchar (50)
);

CREATE TABLE Stock(
                      Stock_id varchar(100)primary key,
                      F_code varchar(10),
                      QtyOnHand int not null,
                      FOREIGN KEY (F_code) REFERENCES Flower(F_code)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Supplier(
                         S_id varchar(10)primary key,
                         Stock_id varchar(10),
                         U_id varchar(10),
                         S_name varchar(50),
                         Adderess varchar(100),
                         TelNo varchar(15),
                         F_code varchar(10),
                         Qty int not null,
                         Price double not null,
                         FOREIGN KEY (F_code) REFERENCES Flower(F_code)ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (U_id) REFERENCES User(U_id)ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Stock_id) REFERENCES Stock(Stock_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE E_OrderDetail(

                              EO_id varchar(10),
                              Stock_id varchar(10),
                              Flower_qty int not null,
                              unitPrice double not null,
                              FOREIGN KEY (Stock_id) REFERENCES Stock(Stock_id)ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (EO_id) REFERENCES E_order(EO_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE P_OrderDetail(
                              PO_id varchar(10),
                              Stock_id varchar(10),
                              P_code varchar(10),
                              Flower_qty int not null,
                              unitPrice double not null,
                              productQTY int not null,
                              Amount double not null,
                              FOREIGN KEY (P_code) REFERENCES Product(P_code)ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (Stock_id) REFERENCES Stock(Stock_id)ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (PO_id) REFERENCES P_order(PO_id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ProductPayment(
                        PaymentId varchar(50)primary key,
                        C_id varchar(10),
                        PO_id varchar(10),
                        PaymentDate DATE,
                        paidPayment double not null,
                        PaymentAmount double not null,
                        balance double not null,
                        FOREIGN KEY (C_id) REFERENCES Customer(C_id)ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (PO_id) REFERENCES P_order(PO_id)ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE EventPayment(
                        PaymentId varchar(50)primary key,
                        C_id varchar(10),
                        EO_id varchar(10),
                        PaymentDate DATE,
                        paidPayment double not null,
                        PaymentAmount double not null,
                        balance double not null,
                        FOREIGN KEY (C_id) REFERENCES Customer(C_id)ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (EO_id) REFERENCES E_order(EO_id)ON UPDATE CASCADE ON DELETE CASCADE
);




insert into User values ('U001','pubudu','1234');


ALTER TABLE Employee ADD TelNo varchar(12);

INSERT INTO Employee (E_id, U_id, E_name, Address, Email, JobRole, TelNo)
VALUES
    ('EMP001', 'U001', 'John Doe', '123 Main St, City, Country', 'john.doe@example.com', 'Manager', '+1234567890'),
    ('EMP002', 'U001', 'Jane Smith', '456 Elm St, City, Country', 'jane.smith@example.com', 'Assistant Manager', '+1987654321'),
    ('EMP003', 'U001', 'Alice Johnson', '789 Oak St, City, Country', 'alice.johnson@example.com', 'Sales Associate', '+1122334455');

ALTER TABLE Customer ADD TelNo varchar(12);

INSERT INTO Customer (C_id, U_id, C_name, Address, TelNo) VALUES
    ('C001', 'U001', 'John Doe', '123 Main Street, Cityville, CA', '555-123-4567'),
    ('C002', 'U001', 'Jane Smith', '456 Elm Avenue, Townsville, NY', '555-987-6543'),
    ('C003', 'U001', 'David Johnson', '789 Oak Lane, Villagetown, TX', '555-456-7890');


//select @@autocommit;
//set @@autocommit = 0;
//commit;
//rollback;

ALTER TABLE Supplier ADD Adderess varchar(100);

INSERT INTO Flower (F_code, F_name) VALUES ('F001', 'Rose');
INSERT INTO Flower (F_code, F_name) VALUES ('F002', 'Lily');
INSERT INTO Flower (F_code, F_name) VALUES ('F003', 'Tulip');

INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES ('RosesStock', 'F001', 1000);
INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES ('LIliStock', 'F002', 1500);
INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES ('TulipStock', 'F003', 20000);
INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES ('DaisyStock', 'F004', 2000);
INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES ('SunFlowerStock', 'F005', 2000);



INSERT INTO Supplier (S_id, Stock_id, U_id, S_name, Adderess, TelNo, F_name, Qty, Price)
VALUES
    ('S001', 'ST001', 'U001', 'Supplier One', '123 Main St', '1234567890', 'Rose', 50.00, 10.00),
    ('S002', 'ST002', 'U001', 'Supplier Two', '456 Elm St', '9876543210', 'Lily', 40.00, 12.50),
    ('S003', 'ST003', 'U001', 'Supplier Three', '789 Oak St', '5556667777', 'Tulip', 30.00, 8.75);


ALTER TABLE P_OrderDetail
DROP COLUMN Description;

     ALTER TABLE Product DROP COLUMN Date;


INSERT INTO Flower (F_code, F_name) VALUES ('F001', 'Rose');
INSERT INTO Flower (F_code, F_name) VALUES ('F002', 'Lily');
INSERT INTO Flower (F_code, F_name) VALUES ('F003', 'Tulip');
INSERT INTO Flower (F_code, F_name) VALUES ('F004', 'Daisy');
INSERT INTO Flower (F_code, F_name) VALUES ('F005', 'Sunflower');

ALTER TABLE Stock DROP COLUMN IsshuedQty;


INSERT INTO Product (P_code, P_name, Qty, Price,issuedFlowers)
VALUES
    ('P001', 'rose boquet', 100.00, 500.00,15.00),
    ('P002', 'lily boquet', 150.00, 750.00,10.00),
    ('P003', 'Tulip boquet', 200.00, 1000.00,20.00);

//ALTER TABLE Stock ADD IsshuedQty varchar(100);







DELETE FROM Supplier WHERE S_id='S1';

DELETE FROM E_order WHERE EO_id='E1';

ALTER TABLE P_order ADD productQTY Double(50,2);

ALTER TABLE Product ADD issuedFlowers Double(50,2);

DELETE FROM Product WHERE P_code='P001';



ALTER TABLE P_order
DROP COLUMN issuedFlowers;

ALTER TABLE Product
DROP COLUMN Qty;]

INSERT INTO Event (E_code, E_name, Date, Price) VALUES
            ('E001', 'Birthday Party', '2024-05-15', 25.0),
            ('E002', 'Wedding Flower Arrangement', '2024-06-20', 35.0),
            ('E003', 'Anniversary Party', '2024-07-10', 40.0);


ALTER TABLE P_order
DROP COLUMN productQTY;

ALTER TABLE ProductPayment ADD balance double not null;

ALTER TABLE P_OrderDetail ADD Amount double not null;
ALTER TABLE P_OrderDetail ADD productQTY int not null;


ALTER TABLE E_order ADD Amount double not null;


ALTER TABLE P_order DROP COLUMN productQTY;
ALTER TABLE P_order DROP COLUMN P_code;

DELETE FROM P_order WHERE PO_id='O1'