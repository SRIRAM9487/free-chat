function SideBarFloatingBtn() {
  return (
    <button
      onClick={() => console.log("New chat")}
      className="
          absolute
          bottom-20
          right-4
          h-12
          w-12
          rounded-full
          bg-[#1677ff]
          text-white
          text-2xl
          flex
          items-center
          justify-center
          shadow-md
          hover:bg-[#0958d9]
          transition
          focus:outline-none
        "
      aria-label="New chat"
    >
      +
    </button>
  );
}

export default SideBarFloatingBtn;
