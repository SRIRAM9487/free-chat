import { useContext, useState } from "react";
import { Button } from "antd";
import { UserContext } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";
import InputField from "../../component/InputField";
import { postService } from "../../script/postService";
import { NotificationContext } from "../../context/NotificationContext";

function Login() {
  const navigate = useNavigate();

  const [errors, setErrors] = useState({});
  const [formData, setFormData] = useState({ userId: "", password: "" });
  const [loading, setloading] = useState(false);
  const { showError, showSuccess } = useContext(NotificationContext);

  const { login } = useContext(UserContext);

  const handleChange = (field, value) => {
    setFormData((prev) => ({ ...prev, [field]: value }));
    if (errors[field]) {
      setErrors((prev) => ({ ...prev, [field]: undefined }));
    }
  };

  const handleSubmit = async (e) => {
    setloading(true);
    e.preventDefault();

    const newErrors = {};

    if (!formData.userId.trim()) newErrors.userId = "userId is required";
    if (!formData.password.trim()) newErrors.password = "password is required";
    setErrors(newErrors);

    if (Object.keys(newErrors).length > 0) {
      setloading(false);
      return;
    }

    console.log("FORM DATA : ", formData);
    try {
      const response = await postService("v1/user/login", formData);
      login(response.data.data);
      navigate("/dashboard");
      showSuccess("Login successful", "success", 1000);
    } catch (error) {
      const message = error?.response?.data?.message;
      console.log("ERROR  : ", error);
      showError(message ? message : "Network error");
    }
    setloading(false);
  };

  const handleCancel = () => {
    clearForm();
    setErrors({});
  };

  const clearForm = () => {
    setFormData({ userId: "", password: "" });
  };

  return (
    <div className="flex items-center justify-center min-h-screen  from-blue-50 to-indigo-100 px-4">
      <div className="bg-white/80 backdrop-blur-xl p-8 rounded-2xl shadow-xl w-full max-w-md border border-white/40">
        <h2 className="text-3xl font-bold mb-8 text-center text-gray-900 tracking-tight">
          Login
        </h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          <InputField
            label="Username"
            placeholder="Enter your username"
            required
            value={formData.userId}
            onChange={(e) => handleChange("userId", e.target.value)}
            error={errors.userId}
            className="h-12 text-base rounded-lg"
          />

          <InputField
            label="Password"
            type="password"
            placeholder="Enter your password"
            required
            value={formData.password}
            onChange={(e) => handleChange("password", e.target.value)}
            error={errors.password}
            className="h-12 text-base rounded-lg"
          />

          <div className="space-y-4 pt-4">
            <Button
              block
              size="large"
              onClick={handleCancel}
              className="!bg-gray-100 !text-gray-800 !border !border-gray-300 hover:!bg-gray-200 rounded-lg h-12 text-base"
            >
              Cancel
            </Button>

            <Button
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
