import React from "react";
import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "antd";
import { UserContext } from "../../context/UserContext";
import { NotificationContext } from "../../context/NotificationContext";
import { postService } from "../../script/postService";
import InputField from "../../component/InputField";

function Login() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({ userId: "", password: "" });
  const [errors, setErrors] = useState({});
  const [loading, setloading] = useState(false);
  const { login } = useContext(UserContext);
  const { showError, showSuccess } = useContext(NotificationContext);

  const validate = () => {
    const newErrors = {};
    if (!formData.userId.trim()) newErrors.userId = "user name is required";
    if (!formData.password.trim()) newErrors.password = "password is required";
    setErrors(newErrors);
    if (Object.keys(newErrors).length > 0) {
      setloading(false);
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setloading(true);

    if (!validate()) return;

    setloading(false);
    try {
      const response = await postService("auth/v1/user/login", formData);
      login(response?.data);
      navigate("/dashboard");
      showSuccess("Login successfull", 1000);
      setloading(false);
    } catch (error) {
      const message = error?.message;
      console.log("Login failed ", error);
      showError(message ? message : "Network error");
    }
  };

  const handleCancel = () => {
    setloading(false);
    setFormData({ userId: "", password: "" });
    setErrors({ userId: "", password: "" });
  };

  return (
    <div className="flex items-center justify-center min-h-screen  from-blue-50 to-indigo-100 px-4">
      <div className="bg-white/80 backdrop-blur-xl p-8   w-full max-w-md border border-white/40">
        <h2 className="text-3xl font-bold mb-8 text-center text-gray-900 tracking-tight">
          Login
        </h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          <InputField
            dataTestId="login-username-inp"
            label="Username"
            placeholder="Enter your username"
            required
            value={formData.userId}
            onChange={(e) =>
              setFormData((prev) => ({ ...prev, userId: e.target.value }))
            }
            disabled={loading}
            error={errors.userId}
          />

          <InputField
            dataTestId="login-password-inp"
            label="Password"
            type="password"
            placeholder="Enter your password"
            value={formData.password}
            onChange={(e) =>
              setFormData((prev) => ({ ...prev, password: e.target.value }))
            }
            required
            disabled={loading}
            error={errors.password}
          />

          <div className="space-y-4 pt-4">
            <Button
              data-testid="login-cancel-btn"
              block
              size="large"
              onClick={handleCancel}
              className="!bg-gray-100 !text-gray-800 !border !border-gray-300 hover:!bg-gray-200 rounded-lg h-12 text-base"
            >
              Cancel
            </Button>

            <Button
              data-testid="login-submit-btn"
              block
              type="primary"
              htmlType="submit"
              size="large"
              loading={loading}
              className="!h-12 !text-base rounded-lg shadow-md hover:shadow-lg"
            >
              Submit
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;
