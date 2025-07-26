import { useEffect, useState } from "react";
import { MoonLoader } from "react-spinners";
import { getGroup } from "../api/groups.js";
import JoinUser from "./JoinUser.jsx";
import UserPanel from "./UserPanel.jsx";
import { logOut } from "../api/auth.js";
import { useNavigate } from "react-router-dom";

function GroupPanel(props) {
  const [loading, setLoading] = useState(true);
  const [status, setStatus] = useState(props.user);
  const [groupData, setGroupData] = useState(null);
  const navigate = useNavigate();

  const leaveGroup = () => {
    logOut();
    navigate("/");
  };

  useEffect(() => {
    loadGroups();
  }, []);

  const loadGroups = async () => {
    const loadedData = await getGroup();
    setGroupData(loadedData);
    setLoading(false);
  }

  const updateData = () => {
    loadGroups();
    console.log("update")
  }

  const renderComponent = () => {
    const lessonsWithUser = groupData.users.flatMap(user =>
    user.lessons.map(lesson => ({
      userId: user.id,
      userName: user.name,
      lesson
    })))
    return(lessonsWithUser.map((lesson, index) => (
    <p>{JSON.stringify(lesson)}</p>
    )));
  }

  if (loading) return <MoonLoader color="#4C0B16" speedMultiplier={0.75} />;

  return (
    <>
      <div>
        <div className="relative font-archivo">
          <h1 className="text-4xl font-bold">{groupData.name}</h1>
          <p className="text-lg py-2">Group Passcode: {groupData.passcode}</p>
          <button 
          className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-xl absolute top-2 right-0"
          onClick={() => leaveGroup()}>Exit Group</button>
          {renderComponent()}
        </div>
        <div className="pt-4 font-archivo">
          {status ? (
            <UserPanel onUpdate={updateData} />
          ) : (
            <JoinUser onJoin={() => setStatus(true)} />
          )}
        </div>
      </div>
    </>
  );
}

export default GroupPanel;
