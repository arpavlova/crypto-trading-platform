USE CRYPTOWORLD;

-- DROP FUNCTION IF EXISTS GetAllUserHoldings;
-- CREATE FUNCTION GetAllUserHoldings(userId INT)
-- RETURNS TABLE (CryptoSymbol VARCHAR(10), Amount DECIMAL(18, 2), CryptoPrice DECIMAL(18, 2))
-- BEGIN
-- SELECT 
--     h.CryptoSymbol,
--     h.Amount,
--     c.Price
-- FROM HasCoins h
-- JOIN CryptoCoins c ON h.CryptoSymbol = c.Symbol
-- WHERE h.UserId = userId
-- END;

DROP PROCEDURE IF EXISTS GetAllUserHoldings;

CREATE PROCEDURE GetAllUserHoldings(IN userId INT)
BEGIN
    SELECT 
        h.CryptoSymbol,
        h.Amount,
        c.Price AS CryptoPrice
    FROM HasCoins h
    JOIN CryptoCoins c ON h.CryptoSymbol = c.Symbol
    WHERE h.UserId = userId;
END;




DROP FUNCTION IF EXISTS GetTotalGains;
CREATE FUNCTION GetTotalGains(userId INT)
RETURNS DECIMAL(18, 2)
DETERMINISTIC
BEGIN
  DECLARE totalBuys DECIMAL(18,2);
  DECLARE totalSells DECIMAL(18,2);
  DECLARE gainLoss DECIMAL(18,2);

  SELECT IFNULL(SUM(CryptoPrice * Amount), 0)
  INTO totalBuys
  FROM Transactions
  WHERE userId = UserId
    AND Type = 'Buy';

  SELECT IFNULL(SUM(CryptoPrice * Amount), 0)
  INTO totalSells
  FROM Transactions
  WHERE userId = UserId
    AND Type = 'Sell';

  IF totalBuys = 0 THEN
    RETURN 0;
  END IF;

  SET gainLoss = (totalSells - totalBuys) / totalBuys;

  RETURN gainLoss;
END;


-- DROP FUNCTION IF EXISTS GetTotalGainsRatio;
-- DELIMITER $
-- CREATE FUNCTION GetTotalGainsRatio(UserId INT, StartDate DATE, EndDate DATE)
-- RETURNS DECIMAL(18, 2)
-- BEGIN
--   DECLARE totalBuys DECIMAL(18,2);
--   DECLARE totalSells DECIMAL(18,2);
--   DECLARE gainLoss DECIMAL(18,2);

--   SELECT IFNULL(SUM(CryptoPrice * Amount), 0) INTO totalBuys
--   FROM Transactions
--   WHERE UserId = userId
--     AND TransactionType = 'Buy'
--     AND DateOfTransaction BETWEEN startDate AND endDate;

--   SELECT IFNULL(SUM(CryptoPrice * Amount), 0) INTO totalSells
--   FROM Transactions
--   WHERE UserId = userId
--     AND TransactionType = 'Sell'
--     AND DateOfTransaction BETWEEN startDate AND endDate;

--   SET gainLoss = totalSells - totalBuys;

--   RETURN gainLoss;
-- END $ DELIMITER;


-- All coins between certain price
-- All coins ...

-- DROP FUNCTION IF EXISTS GetTransactionsByUserInTimePeriod;
-- CREATE FUNCTION GetTransactionsByUserInTimePeriod(User INT, StartDate DATE, EndDate DATE)
-- RETURNS TABLE (TransactionType, VARCHAR(10), TransactionDate DATE, CryptoCoin CHAR(10), CryptoPrice DECIMAL(18, 2), Amount DECIMAL(18, 2))
-- RETURN
--     SELECT Type, DateOfTransaction, CryptoSymbol, CryptoPrice, Amount
--     FROM Transactions
--     WHERE UserId = User
--       AND DateOfTransaction BETWEEN StartDate AND EndDate;