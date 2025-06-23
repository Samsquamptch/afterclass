import { Outlet, Link } from "react-router-dom";

const Layout = () => {
  return (
    <>
      <nav className="bg-birkbeck grid grid-cols-5 font-archivo">
        <div>
        <h1 className="text-white hover:text-gray-300 font-bold text-6xl">
          <Link to="/">Beerbeck</Link></h1>
        </div>
        <div className="">
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
