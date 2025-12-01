function UserActionbtn({ onClick, dataTestId = "icon-delete-btn" }) {
  return (
    <button
      data-testid={dataTestId}
      className="
      p-1
      rounded-md
      text-gray-500
      hover:text-teal-600
      hover:bg-teal-50
      transition-all 
      duration-200
      focus:outline-none
      focus:ring-blue-200 
      focus:ring-opacity-50"
      aria-label="Edit item"
      onClick={onClick}
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="1.5em"
        height="1.5em"
        viewBox="0 0 24 24"
      >
        <path
          fill="currentColor"
          d="M14 11q1.25 0 2.125-.875T17 8t-.875-2.125T14 5t-2.125.875T11 8t.875 2.125T14 11m-6 4.75q1.125-1.325 2.7-2.037T14 13t3.3.713T20 15.75V4H8zM6 18V2h16v16zm-4 4V6h2v14h14v2zM14 9q-.425 0-.712-.288T13 8t.288-.712T14 7t.713.288T15 8t-.288.713T14 9m-3.3 7h6.6q-.725-.5-1.575-.75T14 15t-1.725.25T10.7 16M14 9.875"
        />
      </svg>
    </button>
  );
}

export default UserActionbtn;
