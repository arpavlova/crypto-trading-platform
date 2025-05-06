USE CRYPTOWORLD;

INSERT INTO Users (Username, Balance)
VALUES
  ('alice', 5000.00),
  ('bob', 12000.50),
  ('charlie', 300.00),
  ('diana', 7550.75);

INSERT INTO Users (Username)
VALUES
    ('Andy');
    
INSERT INTO CryptoCoins (Symbol, Price)
VALUES
  ('BTC', 65000.00),
  ('ETH', 3200.50),
  ('SOL', 150.75),
  ('ADA', 0.45);

-- INSERT INTO Transactions (Name, Type, UserId, CryptoSymbol, CryptoPrice, Amount)
-- VALUES
--   ('Buy BTC', 'BUY', 1, 'BTC', 64000.00, 0.1),
--   ('Buy ETH', 'BUY', 2, 'ETH', 3100.00, 2.0),
--   ('Sell SOL', 'SELL', 1, 'SOL', 155.00, 5.0),
--   ('Buy ADA', 'BUY', 3, 'ADA', 0.40, 1000.0);


-- INSERT INTO HasDoneTransactions (UserId, TransactionId)
-- VALUES
--   (1, 1),
--   (2, 2),
--   (1, 3),
--   (3, 4);


-- INSERT INTO HasCoins (UserId, CryptoSymbol, Amount)
-- VALUES
--   (1, 'BTC', 0.1),
--   (2, 'ETH', 2.0),
--   (1, 'SOL', 0.0),
--   (3, 'ADA', 1000.0);
