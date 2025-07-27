import { Outlet, Link } from "react-router-dom";

const Layout = () => {
  return (
    <>
      <nav className="bg-birkbeck font-archivo relative pb-16">
        <div>
        <h1 className="text-white hover:text-gray-300 font-bold text-6xl pb-1 absolute top-0 left-4">
          <Link to="/">Beerbeck</Link></h1>
        </div>
        <div className="absolute right-4 top-0">
        <ul className="text-white text-left text-2xl">
          <li className="hover:text-gray-300">
            <Link to="/groups">Groups</Link>
          </li>
          <li className="hover:text-gray-300">
            <Link to="/about">About</Link>
          </li>
        </ul>
        </div>
      </nav>

      <Outlet />
    </>
  )
};

export default Layout;
