import React, { useEffect, useState } from "react";

const PAIRS = [
  "XBT/USD", "ETH/USD", "ADA/USD", "XRP/USD", "SOL/USD",
  "DOT/USD", "LTC/USD", "BCH/USD", "DOGE/USD", "AVAX/USD",
  "LINK/USD", "MATIC/USD", "ATOM/USD", "ETC/USD", "XLM/USD",
  "EOS/USD", "TRX/USD", "UNI/USD", "NEAR/USD", "FIL/USD",
];

const Market = () => {
  
  const [prices, setPrices] = useState({});

  useEffect(() => {
    const ws = new WebSocket("wss://ws.kraken.com/");
    
    ws.onopen = () => {
      ws.send(
        JSON.stringify({
          event: "subscribe",
          pair: PAIRS,
          subscription: { name: "ticker" },
        })
      );
    };

    ws.onmessage = (msg) => {
      const data = JSON.parse(msg.data);
      if (Array.isArray(data) && data[1]?.c) {
        const pairName = data[3];
        const price = data[1].c[0]; // last trade price
        setPrices((prev) => ({ ...prev, [pairName]: price }));
      }
    };

    
    return () => ws.close();
  }, []);

  return (
    <div className="market-screen">
      <h2>Top 20 Cryptocurrencies</h2>
      <table className="market-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price (USD)</th>
          </tr>
        </thead>
        <tbody>
          {PAIRS.map((pair) => (
            <tr key={pair}>
              <td>{pair}</td>
              <td>${prices[pair] ? parseFloat(prices[pair]).toFixed(2) : "Loading..."}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
export default Market;