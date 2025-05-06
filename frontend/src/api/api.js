const BASE = process.env.REACT_APP_API_BASE_URL;
console.log("API Base URL:", BASE);

export const getBalance = (userId) =>
  fetch(`${BASE}/user/${userId}/balance`)
    .then((res) => res.json());

export const deposit = (userId, amount) =>
  fetch(`${BASE}/user/${userId}/deposit`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ amount }),
  });

export const withdraw = (userId, amount) =>
  fetch(`${BASE}/user/${userId}/withdraw`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ amount }),
  });

export const buy = (userId, cryptoSymbol, amount) =>
  fetch(`${BASE}/trade/${userId}/buy`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ cryptoSymbol, amount }),
  }
);

export const sell = (userId, cryptoSymbol, amount) =>
  fetch(`${BASE}/trade/${userId}/sell`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ cryptoSymbol, amount }),
  });


export const getTransactions = (userId) =>
  fetch(`${BASE}/trade/transactions/${userId}`)
    .then((res) => res.json());

export const reset = (userId) =>
  fetch(`${BASE}/user/${userId}/reset`, {
    method: "POST",
  });
