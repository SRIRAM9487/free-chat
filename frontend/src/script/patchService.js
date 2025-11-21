import axios from "axios";

const API_BASE_URL = import.meta.env.VITE_SERVER_URL;

export const patchService = async (endpoint, data) => {
  const response = await axios.patch(`${API_BASE_URL}/${endpoint}`, data, {
    headers: {
      "Content-Type": "application/json",
    },
  });
  return response.data;
};
