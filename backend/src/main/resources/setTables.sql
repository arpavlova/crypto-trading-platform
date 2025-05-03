USE CRYPTOWORLD;

CREATE TABLE Users (
  Id INT NOT NULL PRIMARY KEY GENERATED
  ALWAYS AS IDENTITY
(START WITH 1,INCREMENT BY 1),
  Username VARCHAR(100),
  DateOfRegistration DATETIME(fsp) DEFAULT ON UPDATE,
  Balance DECIMAL(18, 2) DEFAULT 10000,

  PRIMARY KEY(Id)
);

CREATE TABLE CryptoCoins (
    Symbol VARCHAR(10) NOT NULL,
    Price DECIMAL(18, 2) DEFAULT 0.00,

    PRIMARY KEY(Symbol),
)

CREATE TABLE Transactions (
  Id INT NOT NULL GENERATED
ALWAYS AS IDENTITY
(START WITH 1,INCREMENT BY 1),
  Name VARCHAR(100),
  Type VARCHAR(10),
  DateOfTransaction DATETIME(fsp) DEFAULT ON UPDATE,
  UserId INT,
  CryptoSymbol VARCHAR(10),
  CryptoPrice DECIMAL(18, 2),
  Amount DECIMAL(18, 2),

  PRIMARY KEY(Id),
  FOREIGN KEY (UserId)
      REFERENCES Users(Id),
    FOREIGN KEY(CryptoSymbol)
        REFERENCES CryptoCoins(Symbol)

);

CREATE TABLE HasDoneTransactions (
  UserId INT NOT NULL,
  TransactionId INT NOT NULL,

  PRIMARY KEY (UserId, TransactionId),
   FOREIGN KEY (UserId)
    REFERENCES Users(Id),
    FOREIGN KEY (TransactionId)
    REFERENCES Transactions(Id)
);

CREATE TABLE HasCoins (
  UserId INT NOT NULL,
  CryptoSymbol VARCHAR(10) NOT NULL,
  Amount DECIMAL(18, 2) DEFAULT 0.00,

  PRIMARY KEY (UserId, CryptoSymbol),
       FOREIGN KEY (UserId)
    REFERENCES Users(Id),
    FOREIGN KEY (CryptoSymbol)
    REFERENCES CryptoCoins(Symbol)
);

ALTER TABLE Authors ADD CONSTRAINT CK_JB_SAL CHECK (YearOfFirstPublishedBook <= YearOfLastPublishedBook); - commented