import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-[#f5f5f5] px-4">
      <div className="max-w-md w-full bg-white rounded-lg   p-8 text-center">
        <h1 className="text-5xl font-semibold text-[#262626]">404</h1>

        <p className="mt-2 text-lg font-medium text-[#262626]">
          Page Not Found
        </p>

        <p className="mt-3 text-sm text-[#595959]">
          Sorry, the page you visited does not exist.
        </p>

        <div className="mt-6 flex justify-center gap-3">
          <Link
            to="/role"
            className="
              px-5 py-2
              rounded-md
              bg-[#1677ff]
              text-white
              text-sm
              font-medium
              hover:bg-[#0958d9]
              transition
            "
          >
            Back Home
          </Link>

          <button
            onClick={() => window.history.back()}
            className="
              px-5 py-2
              rounded-md
              border
              border-[#d9d9d9]
              text-sm
              font-medium
              text-[#262626]
              hover:border-[#1677ff]
              hover:text-[#1677ff]
              transition
            "
          >
            Previous Page
          </button>
        </div>
      </div>
    </div>
  );
}
