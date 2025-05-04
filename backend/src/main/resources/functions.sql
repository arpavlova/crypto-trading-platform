USE CRYPTOWORLD;


DROP FUNCTION IF EXISTS GetTransactionsByUser;
CREATE FUNCTION GetTransactionsByUser(User INT)
RETURNS TABLE (TransactionType, VARCHAR(10), TransactionDate DATETIME(fsp), CryptoCoin CHAR(10), CryptoPrice DECIMAL(18, 2), Amount DECIMAL(18, 2))
    RETURN
        SELECT Type, DateOfTransaction, CryptoSymbol, CryptoPrice, Amount FROM Transactions
        WHERE UserId = User;

DROP FUNCTION IF EXISTS GetTransactionsByUserInTimePeriod;
CREATE FUNCTION GetTransactionsByUserInTimePeriod(User INT, StartDate DATE, EndDate DATE)
DROP FUNCTION IF EXISTS GetTransactionsByUserInTimePeriod;
RETURNS TABLE (TransactionType, VARCHAR(10), TransactionDate DATETIME(fsp), CryptoCoin CHAR(10), CryptoPrice DECIMAL(18, 2), Amount DECIMAL(18, 2))
RETURN
    SELECT Type, DateOfTransaction, CryptoSymbol, CryptoPrice, Amount
    FROM Transactions
    WHERE UserId = User
      AND DateOfTransaction BETWEEN StartDate AND EndDate;




DROP FUNCTION IF EXISTS GetTotalGainsRatio;
DELIMITER $
CREATE FUNCTION GetTotalGainsRatio(UserId INT, StartDate DATE, EndDate DATE)
RETURNS DECIMAL(18, 2)
BEGIN
  DECLARE totalBuys DECIMAL(18,2);
  DECLARE totalSells DECIMAL(18,2);
  DECLARE gainLoss DECIMAL(18,2);

  SELECT IFNULL(SUM(CryptoPrice * Amount), 0) INTO totalBuys
  FROM Transactions
  WHERE UserId = userId
    AND TransactionType = 'Buy'
    AND DateOfTransaction BETWEEN startDate AND endDate;

  SELECT IFNULL(SUM(CryptoPrice * Amount), 0) INTO totalSells
  FROM Transactions
  WHERE UserId = userId
    AND TransactionType = 'Sell'
    AND DateOfTransaction BETWEEN startDate AND endDate;

  SET gainLoss = totalSells - totalBuys;

  RETURN gainLoss;
END $ DELIMITER;





SELECT * FROM TABLE(FN24_45750.GetBooksByYear(1980)) T;
SELECT * FROM TABLE(FN24_45750.GetBooksByAuthorAlias('Isaac Asimov')) T;

