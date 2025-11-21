import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_SERVER_URL;

export const getService = async (endpoint) => {
  const response = await axios.get(`${API_BASE_URL}/${endpoint}`, {
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response.data;
};
