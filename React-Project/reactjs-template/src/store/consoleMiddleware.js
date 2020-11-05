export const consoleMiddleware = (store) => (next) => (action) => {
  console.log(`[${action.type}]`, action.payload);
  next(action);
};
